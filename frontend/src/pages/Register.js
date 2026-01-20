import React, { useState } from "react";
import api from "../services/api";

function Register() {
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");

  const registerJobSeeker = async () => {
    await api.post("/auth/register/jobseeker", {
      fullName,
      email,
      password: "test123"
    });
    alert("Registered successfully");
  };

  return (
    <div style={{ padding: 40 }}>
      <h2>Register Job Seeker</h2>
      <input placeholder="Full Name" onChange={(e) => setFullName(e.target.value)} /><br /><br />
      <input placeholder="Email" onChange={(e) => setEmail(e.target.value)} /><br /><br />
      <button onClick={registerJobSeeker}>Register</button>
    </div>
  );
}

export default Register;
