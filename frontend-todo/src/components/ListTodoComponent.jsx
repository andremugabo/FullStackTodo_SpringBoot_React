import React, { useState } from 'react';

const ListTodoComponent = () => {
    const dummyData = [
        {
            id: "12282a13-a313-4802-bdae-02e046d9d672",
            active: true,
            title: "Learn Python",
            description: "Learn Python with project",
            completed: false
        },
        {
            id: "1919ad17-22a8-49d4-9cdc-8cbab186ca5b",
            active: true,
            title: "Learn Hibernate",
            description: "Learn Hibernate with project",
            completed: false
        },
        {
            id: "e1b5e5a5-dc75-462a-bbe6-487e2af9bf22",
            active: true,
            title: "Learn Java",
            description: "Learn Java with Todo project",
            completed: false
        },
        {
            id: "d9900a7e-8ac9-4da4-b440-0d51a02153e8",
            active: true,
            title: "Learn JavaScript",
            description: "Learn JavaScript with project",
            completed: false
        }
    ];

    const [todos, setTodos] = useState(dummyData);

    const handleEdit = (id) => {
        console.log("Edit todo:", id);
    };

    const handleDelete = (id) => {
        console.log("Delete todo:", id);
    };

    return (
        <div className="container mt-4">
            <h2 className="text-center mb-4">List of Todos</h2>
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
                        {todos.map((todo, index) => (
                            <tr key={todo.id}>
                                <td>{index + 1}</td>
                                <td>{todo.title}</td>
                                <td>{todo.description}</td>
                                <td className='text-center align-middle'>
                                    <span className={`badge ${todo.completed ? 'bg-success' : 'bg-danger'}`}>
                                        {todo.completed ? 'Yes' : 'No'}
                                    </span>
                                </td>
                                <td className='text-center align-middle'>
                                    <span className={`badge ${todo.active ? 'bg-primary' : 'bg-secondary'}`}>
                                        {todo.active ? 'Active' : 'Inactive'}
                                    </span>
                                </td>
                                <td className='text-center align-middle'>
                                    <button
                                        onClick={() => handleEdit(todo.id)}
                                        className="btn btn-warning btn-sm me-2"
                                    >
                                        ✍🏽 Edit
                                    </button>
                                    <button
                                        onClick={() => handleDelete(todo.id)}
                                        className="btn btn-danger btn-sm"
                                    >
                                        🗑️ Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default ListTodoComponent;
