import axios from "axios";
import { getToken } from "./AuthService";



const BASE_REST_API_URL = 'http://localhost:8080/api/todos';



// Add a request interceptor
axios.interceptors.request.use(function (config) {
    // Do something before request is sent
    config.headers['Authorization'] = getToken();
    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  },
);



export const getAllTodos = (page = 0, size = 10) =>  {
    return axios.get(BASE_REST_API_URL + "/",{
        params: {page, size}
    })
}
export const createTodo = (todo) => axios.post(BASE_REST_API_URL + "/create",todo);
export const getTodoById = (id) => axios.get(BASE_REST_API_URL + "/"+id);
export const updateTodo = (id,todo) => axios.put(BASE_REST_API_URL + "/"+id,todo);
export const deleteTodo = (id) => axios.put(BASE_REST_API_URL + "/" + id +"/soft-delete");
export const completeTodo = (id) => axios.patch(BASE_REST_API_URL + "/" + id + "/complete");

