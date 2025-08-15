import React, { useEffect, useState } from 'react';
import { createTodo, getTodoById, updateTodo } from '../services/TodoService';
import { useNavigate, useParams } from 'react-router-dom';

export const TodoComponent = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null); 

  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      setLoading(true);
      setError(null); 
      getTodoById(id)
        .then((response) => {
          if (response.data) {
            setTitle(response.data.title);
            setDescription(response.data.description);
          } else {
            setError('No data received from server.');
          }
        })
        .catch((err) => {
          console.error(err);
          setError('Server is not running or unreachable.');
        })
        .finally(() => setLoading(false));
    } else {
      setLoading(false); 
    }
  }, [id]);

  const displayStatus = () => {
    if (loading) {
      return (
        <div className="spinner-container text-center">
          <div className="spinner"></div>
          <p>Loading...</p>
        </div>
      );
    }

    if (error) {
      return (
        <div className="alert alert-danger text-center">
          {error}
        </div>
      );
    }

    return null;
  };

  const saveTodo = (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    const todo = { title, description };

    if(id){
        updateTodo(id,todo)
        .then((response) => {
            navigate('/todos')
        })
        .catch(error => {
            console.error(error);
        })
    }else{
        createTodo(todo)
      .then((response) => {
        navigate('/todos');
      })
      .catch((err) => {
        console.error(err);
        setError('Failed to save Todo. Server may be down.');
      })
      .finally(() => setLoading(false));
    }

    
  };

  const pageTitle = id ? 'Update Todo' : 'Add Todo';

  return (
    <div className="container p-5">
      <div className="card col-md-6 offset-md-3">
        <h2 className="text-center">{pageTitle}</h2>
        {displayStatus()}
        {!loading && !error && (
          <div className="card-body">
            <form onSubmit={saveTodo}>
              <div className="form-group mb-2">
                <label className="form-label">Todo Title:</label>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Enter Todo Title"
                  value={title}
                  onChange={(e) => setTitle(e.target.value)}
                  required
                />
              </div>
              <div className="form-group mb-2">
                <label className="form-label">Todo Description:</label>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Enter Description"
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
                  required
                />
              </div>
              <button type="submit" className="btn btn-success w-100">
                {id ? 'Update Todo' : 'Create Todo'}
              </button>
            </form>
          </div>
        )}
      </div>
    </div>
  );
};
