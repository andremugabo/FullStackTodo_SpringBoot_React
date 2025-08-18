import React, { useState } from 'react'
import { registerApiCall } from '../services/AuthService'
import { NavLink, useNavigate } from 'react-router-dom'

const Register = () => {
  const [name, setName] = useState('')
  const [username, setUsername] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [message, setMessage] = useState('')
  const [messageType, setMessageType] = useState('info') // "success" | "danger" | "info"
  const [loading, setLoading] = useState(false)
  const navigate = useNavigate()

  const handleRegistration = async (e) => {
    e.preventDefault()

    if (!name || !username || !email || !password) {
      setMessage("⚠️ Please fill in all fields")
      setMessageType("danger")
      return
    }

    setLoading(true)
    setMessage("")

    const register = { name, username, email, password }

    try {
      const response = await registerApiCall(register)
      console.log(response.status)

      if (response.status === 201) {
        setMessage("✅ Registration successful! Redirecting...")
        setMessageType("success")
        setTimeout(() => navigate("/login"), 1500)
      } else {
        setMessage("❌ Something went wrong. Please try again.")
        setMessageType("danger")
      }
    } catch (error) {
      console.error(error)
      setMessage("❌ Registration failed. Please try again later.")
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
              <h2 className='text-center'>User Registration Form</h2>
            </div>
            <div className='card-body'>
              <form onSubmit={handleRegistration}>
                <div className='row mb-3'>
                  <label className='col-md-3 control-label'>Name</label>
                  <div className='col-md-9'>
                    <input
                      type="text"
                      name="name"
                      className='form-control'
                      placeholder='Enter name'
                      value={name}
                      onChange={(e) => setName(e.target.value)}
                      disabled={loading}
                    />
                  </div>
                </div>

                <div className='row mb-3'>
                  <label className='col-md-3 control-label'>Email</label>
                  <div className='col-md-9'>
                    <input
                      type="email"
                      name="email"
                      className='form-control'
                      placeholder='Enter Email'
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      disabled={loading}
                    />
                  </div>
                </div>

                <div className='row mb-3'>
                  <label className='col-md-3 control-label'>Username</label>
                  <div className='col-md-9'>
                    <input
                      type="text"
                      name="username"
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
                      name="password"
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
                        Registering...
                      </span>
                    ) : (
                      "Register"
                    )}
                  </button>
                </div>

                <div className='text-center'>
                  <span>
                    Already have an account?{" "}
                    <NavLink to="/login">Login</NavLink>
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

export default Register
