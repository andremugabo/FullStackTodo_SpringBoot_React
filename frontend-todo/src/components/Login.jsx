import React, { useState } from 'react'
import { useNavigate, NavLink } from 'react-router-dom'
import { loginAPICall, saveLoggedInUser, storeToken } from '../services/AuthService'

const Login = () => {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [loading, setLoading] = useState(false)
  const [message, setMessage] = useState('')
  const [messageType, setMessageType] = useState('info')
  const navigate = useNavigate()

  const handleLogin = async (e) => {
    e.preventDefault()

    if (!username || !password) {
      setMessage("⚠️ Please enter both username and password")
      setMessageType("danger")
      return
    }

    setLoading(true)
    setMessage("")

    try {
      // const loginObj  = { username, password }
      const response = await loginAPICall(username, password)
      console.log(response.data)
      const token = 'Basic ' + window.btoa(username + ":" + password)
      storeToken(token);
      saveLoggedInUser(username);
    
      if (response.status === 200) {
        setMessage("✅ Login successful! Redirecting...")
        setMessageType("success")
        setTimeout(() => {
          navigate("/todos")
      window.location.reload(false)

        }, 2000)
        
      } else {
        setMessage("❌ Invalid credentials. Please try again.")
        setMessageType("danger")
      }
    } catch (error) {
      console.error(error)
      setMessage("❌ Login failed. Please try again later.")
      setMessageType("danger")
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className='container mt-5'>
      <div className='row'>
        <div className='col-md-6 offset-md-3'>
          <div className='card'>
            <div className='card-header'>
              <h2 className='text-center'>Login</h2>
            </div>
            <div className='card-body'>
              <form onSubmit={handleLogin}>
                <div className='row mb-3'>
                  <label className='col-md-3 control-label'>Username</label>
                  <div className='col-md-9'>
                    <input
                      type="text"
                      className='form-control'
                      placeholder='Enter Username'
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                      disabled={loading}
                    />
                  </div>
                </div>

                <div className='row mb-3'>
                  <label className='col-md-3 control-label'>Password</label>
                  <div className='col-md-9'>
                    <input
                      type="password"
                      className='form-control'
                      placeholder='Enter Password'
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      disabled={loading}
                    />
                  </div>
                </div>

                {message && (
                  <div className={`alert alert-${messageType}`}>{message}</div>
                )}

                <div className='form-group mb-3'>
                  <button
                    type="submit"
                    className='btn btn-primary w-100'
                    disabled={loading}
                  >
                    {loading ? (
                      <span>
                        <span
                          className='spinner-border spinner-border-sm me-2'
                          role="status"
                        />
                        Logging in...
                      </span>
                    ) : (
                      "Login"
                    )}
                  </button>
                </div>

                <div className='text-center'>
                  <span>
                    Don’t have an account?{" "}
                    <NavLink to="/register">Register</NavLink>
                  </span>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Login
