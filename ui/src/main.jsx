import React, { useState, useEffect } from "react";
import { createRoot } from "react-dom/client";

const API = import.meta.env.VITE_API_BASE || "http://localhost:8080";

function App(){
  const [token,setToken]=useState("");
  const [form,setForm]=useState({username:"admin",password:"Admin@123"});
  const [rows,setRows]=useState([]);

  const login = async () => {
    const res = await fetch(`${API}/auth/login`, {method:"POST", headers:{"Content-Type":"application/json"}, body: JSON.stringify(form)});
    const js = await res.json(); setToken(js.token || "");
  };

  const load = async () => {
    const res = await fetch(`${API}/api/talents`, {headers:{"Authorization":`Bearer ${token}`}});
    const js = await res.json(); setRows(js.content || []);
  };

  useEffect(()=>{ if(token) load(); },[token]);

  return (
    <div className="max-w-4xl mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">Talent Supply</h1>
      {!token ? (
        <div className="p-4 bg-white rounded-xl shadow">
          <div className="flex gap-2">
            <input className="border p-2 rounded w-48" value={form.username} onChange={e=>setForm({...form,username:e.target.value})} placeholder="username"/>
            <input className="border p-2 rounded w-48" type="password" value={form.password} onChange={e=>setForm({...form,password:e.target.value})} placeholder="password"/>
            <button className="px-4 py-2 bg-black text-white rounded" onClick={login}>Login</button>
          </div>
        </div>
      ):(
        <div className="p-4 bg-white rounded-xl shadow">
          <div className="mb-3 flex justify-between">
            <div className="font-semibold">Logged in</div>
            <button className="text-sm underline" onClick={()=>setToken("")}>Logout</button>
          </div>
          <table className="w-full text-sm">
            <thead><tr className="text-left">
              <th className="py-2">Name</th><th>Email</th><th>Skill</th><th>Exp</th><th>Status</th>
            </tr></thead>
            <tbody>
              {rows.map(r=>(
                <tr key={r.id} className="border-t">
                  <td className="py-2">{r.name}</td>
                  <td>{r.email}</td>
                  <td>{r.primarySkill}</td>
                  <td>{r.yearsExp}</td>
                  <td>{r.currentStatus}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

createRoot(document.getElementById("root")).render(<App/>);
