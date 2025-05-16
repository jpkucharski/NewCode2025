import React, { useState } from "react";
import { generateHmac } from "./crypto";

const BACKEND_URL = "http://localhost:8081/api/verify";
const SECRET_KEY = "super-secret-key";

function App() {
  const [response, setResponse] = useState("");

  const jsonData = {
    transactionId: "12345",
    items: [
      { id: "item1", price: 100 },
      { id: "item2", price: 150 },
    ],
  };

  const handleSend = async () => {
    const jsonString = JSON.stringify(jsonData);
    const hash = generateHmac(jsonData, SECRET_KEY);

    try {
      const res = await fetch(BACKEND_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          json: jsonString,
          hash: hash,
        }),
      });

      const data = await res.json();
      setResponse(JSON.stringify(data, null, 2));
    } catch (err) {
      setResponse("Error: " + err.message);
    }
  };

  return (
    <div style={{ padding: "2rem" }}>
      <h2>Send JSON with Hash</h2>
      <button onClick={handleSend}>Send to Backend</button>
      <pre>{response}</pre>
    </div>
  );
}

export default App;