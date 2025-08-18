import React, { useEffect, useState } from 'react';
import { getAllTodos,deleteTodo, completeTodo} from '../services/TodoService';
import loadingGif from '../assets/loadingGif.webp';
import { useNavigate } from 'react-router-dom';

const ListTodoComponent = () => {
    const [todos, setTodos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [page, setPage] = useState(1);
    const [limit] = useState(5); // items per page
    const [hasMore, setHasMore] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        listTodos();
    }, [page]);


  
    const listTodos = () => {
        setLoading(true);
        getAllTodos(page, limit)
            .then((response) => {
                setTodos(response.data.content);
                setHasMore(response.data.content.length === limit); 
                setLoading(false);
            })
            .catch((error) => {
                console.error(error);
                setError("Cannot connect to server. Please try again later.");
                setLoading(true);
            });
    };

    const handleEdit = (id) => navigate(`/update/${id}`);

    const handleDelete = (id) => {
        if (window.confirm("Are you sure you want to delete this todo?")) {
            deleteTodo(id)
                .then((response) => {
                    listTodos()
                })
                .catch(err => console.error(err));
        }
    };


    const handleComplete = (id) => {
        if(window.confirm("Are you sure you want to complete this todo")){
            completeTodo(id).then((response)=>{
                listTodos()
            }).catch(err => console.error(err))
        }
    }

    const addNewTodo = () => navigate('/create');

    const goToPreviousPage = () => {
        if (page > 1) setPage(page - 1);
    };

    const goToNextPage = () => {
        if (hasMore) setPage(page + 1);
    };

    if (loading) {
        return (
            <div className="d-flex flex-column align-items-center justify-content-center vh-100">
                <img src={loadingGif} alt="Loading todos..." style={{ width: '150px', height: '150px' }} />
                <p className="mt-3 text-muted fs-5">Fetching your todos, please wait..</p>
            </div>
        );
    }

    if (error) {
        return (
            <div className="d-flex flex-column align-items-center justify-content-center vh-100">
                <p className="fs-5 text-danger">{error}</p>
                <button className="btn btn-primary mt-3" onClick={listTodos}>
                    Retry
                </button>
            </div>
        );
    }

    return (
        <div className="container mt-4">
            <h2 className="text-center mb-4">List of Todos</h2>
            <button className="btn btn-primary mb-3" onClick={addNewTodo}>Add Todo</button>

            <div className="table-responsive">
                <table className="table table-bordered table-striped">
                    <thead className="table-light">
                        <tr>
                            <th>#</th>
                            <th>Todo Title</th>
                            <th>Todo Description</th>
                            <th>Todo Completed</th>
                            <th>Todo Active</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {todos.length === 0 ? (
                            <tr>
                                <td colSpan="6" className="text-center text-muted">
                                    No todos found. Click "Add Todo" to create one!
                                </td>
                            </tr>
                        ) : (
                            todos.map((todo, index) => (
                                <tr key={todo.id}>
                                    <td>{(page - 1) * limit + index + 1}</td>
                                    <td>{todo.title}</td>
                                    <td>{todo.description}</td>
                                    <td className="text-center align-middle">
                                        <span className={`badge ${todo.completed ? 'bg-success' : 'bg-danger'}`}>
                                            {todo.completed ? 'Yes' : 'No'}
                                        </span>
                                    </td>
                                    <td className="text-center align-middle">
                                        <span className={`badge ${todo.active ? 'bg-primary' : 'bg-secondary'}`}>
                                            {todo.active ? 'Active' : 'Inactive'}
                                        </span>
                                    </td>
                                    <td className="text-center align-middle">
                                        <button onClick={() => handleEdit(todo.id)} className="btn btn-warning btn-sm me-2" aria-label={`Edit todo ${todo.title}`}>✍🏽 Edit</button>
                                        <button onClick={() => handleDelete(todo.id)} className="btn btn-danger btn-sm me-2" aria-label={`Delete todo ${todo.title}`}>🗑️ Delete</button>
                                        <button onClick={() => handleComplete(todo.id)} className="btn btn-success btn-sm" aria-label={`Complete todo ${todo.title}`}>✅ Complete</button>
                                    </td>
                                </tr>
                            ))
                        )}
                    </tbody>
                </table>
            </div>

            {/* Pagination */}
            <div className="d-flex justify-content-between mt-3">
                <button className="btn btn-outline-primary" onClick={goToPreviousPage} disabled={page === 1}>
                    Previous
                </button>
                <span>Page {page}</span>
                <button className="btn btn-outline-primary" onClick={goToNextPage} disabled={!hasMore}>
                    Next
                </button>
            </div>
        </div>
    );
};

export default ListTodoComponent;
