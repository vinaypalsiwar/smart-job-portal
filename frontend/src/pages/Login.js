import React from "react";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  return (
    <div style={{ padding: 40 }}>
      <h2>Login</h2>

      <button onClick={() => navigate("/jobseeker")}>
        Login as Job Seeker
      </button>

      <br /><br />

      <button onClick={() => navigate("/recruiter")}>
        Login as Recruiter
      </button>
    </div>
  );
}

export default Login;
