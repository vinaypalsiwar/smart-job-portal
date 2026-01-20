import React, { useEffect, useState } from "react";
import api from "../services/api";
import ResumeUpload from "./ResumeUpload";

function JobSeekerDashboard() {

  const [jobs, setJobs] = useState([]);
  const [atsResult, setAtsResult] = useState(null);

  const userEmail = "jobseeker@test.com"; // must exist in DB

  useEffect(() => {
    api.get("/jobs/all").then((res) => setJobs(res.data));
  }, []);

  const checkATS = async (jobId) => {
    try {
      const res = await api.get(`/ats/score/${jobId}/${userEmail}`);
      setAtsResult(res.data);
    } catch (err) {
      alert("ATS score not available. Upload resume first.");
    }
  };

  return (
    <div style={{ padding: 30 }}>

      <h2>Job Seeker Dashboard</h2>

      {/* Resume Upload */}
      <ResumeUpload userEmail={userEmail} />

      <h3>Available Jobs</h3>

      {jobs.map((job) => (
        <div key={job.id}
             style={{ border: "1px solid #ccc", padding: 15, marginBottom: 15 }}>

          <h4>{job.title}</h4>
          <p><b>Skills Required:</b> {job.skillsRequired}</p>

          <button onClick={() => checkATS(job.id)}>
            Check ATS Score
          </button>
        </div>
      ))}

      {/* ATS RESULT */}
      {atsResult && (
        <div style={{ marginTop: 30, border: "2px solid green", padding: 20 }}>
          <h3>ATS Result</h3>
          <p><b>ATS Score:</b> {atsResult.atsScore}%</p>

          <p><b>Matched Skills:</b></p>
          <ul>
            {atsResult.matchedSkills.map((s, i) => (
              <li key={i}>{s}</li>
            ))}
          </ul>

          <p><b>Missing Skills:</b></p>
          <ul>
            {atsResult.missingSkills.map((s, i) => (
              <li key={i}>{s}</li>
            ))}
          </ul>
        </div>
      )}

    </div>
  );
}

export default JobSeekerDashboard;
