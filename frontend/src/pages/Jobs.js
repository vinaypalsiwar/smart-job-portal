import React, { useEffect, useState } from "react";
import api from "../services/api";

function Jobs() {
  const [jobs, setJobs] = useState([]);

  useEffect(() => {
    api.get("/jobs/all").then((res) => setJobs(res.data));
  }, []);

  return (
    <div style={{ padding: 40 }}>
      <h2>Available Jobs</h2>
      <ul>
        {jobs.map((job) => (
          <li key={job.id}>
            <b>{job.title}</b> - {job.location}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Jobs;
