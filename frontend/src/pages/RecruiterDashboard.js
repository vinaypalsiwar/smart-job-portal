import React, { useEffect, useState } from "react";
import api from "../services/api";

function RecruiterDashboard() {

  const [jobs, setJobs] = useState([]);
  const [rankings, setRankings] = useState([]);

  useEffect(() => {
    api.get("/jobs/all").then(res => setJobs(res.data));
  }, []);

  const viewRanking = async (jobId) => {
    const res = await api.get(`/recruiter/rank/${jobId}`);
    setRankings(res.data);
  };

  return (
    <div style={{ padding: 30 }}>
      <h2>Recruiter Dashboard</h2>

      <h3>Your Jobs</h3>
      {jobs.map(job => (
        <div key={job.id}
             style={{ border: "1px solid gray", padding: 10, marginBottom: 10 }}>
          <b>{job.title}</b><br />
          <button onClick={() => viewRanking(job.id)}>
            View ATS Ranking
          </button>
        </div>
      ))}

      {rankings.length > 0 && (
        <div style={{ marginTop: 30 }}>
          <h3>Candidate Ranking (ATS)</h3>
          <table border="1" cellPadding="10">
            <thead>
              <tr>
                <th>Candidate</th>
                <th>ATS Score</th>
              </tr>
            </thead>
            <tbody>
              {rankings.map((r, i) => (
                <tr key={i}>
                  <td>{r.candidateEmail}</td>
                  <td>{r.atsScore}%</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default RecruiterDashboard;
