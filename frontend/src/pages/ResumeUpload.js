import React, { useState } from "react";
import api from "../services/api";

function ResumeUpload({ userEmail }) {
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState("");

  const uploadResume = async () => {
    if (!file) {
      alert("Please select a resume");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      await api.post(`/resumes/upload/${userEmail}`, formData);
      setMessage("✅ Resume uploaded successfully");
    } catch (err) {
      console.error(err);
      setMessage("❌ Resume upload failed");
    }
  };

  return (
    <div>
      <h3>Upload Resume</h3>
      <input
        type="file"
        accept=".pdf"
        onChange={(e) => setFile(e.target.files[0])}
      />
      <br /><br />
      <button onClick={uploadResume}>Upload Resume</button>
      <p>{message}</p>
    </div>
  );
}

export default ResumeUpload;
