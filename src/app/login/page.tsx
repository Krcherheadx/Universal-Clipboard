"use client";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { motion } from "framer-motion";
import { LockClosedIcon } from "@heroicons/react/24/solid";

export default function LoginPage() {
  const router = useRouter();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        throw new Error("فشل تسجيل الدخول");
      }

      const data = await response.json();
      localStorage.setItem("token", data.token); // assuming response is { token: "..." }
      localStorage.setItem("username", username);
      router.push("/dashboard");
    } catch (err) {
      alert("خطأ في تسجيل الدخول");
      console.error(err);
    }
  };

  return (
    <div
      dir="rtl"
      className="min-h-screen bg-gradient-to-br from-slate-100 via-slate-200 to-slate-300 flex items-center justify-center p-6 font-[Rubik]"
    >
      <motion.div
        className="bg-white/60 backdrop-blur-lg rounded-2xl shadow-2xl ring-1 ring-slate-200 border border-slate-300 p-8 max-w-md w-full space-y-6"
        initial={{ opacity: 0, y: 40 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.6 }}
      >
        <div className="flex justify-center">
          <LockClosedIcon className="h-10 w-10 text-indigo-500 drop-shadow" />
        </div>
        <h1 className="text-3xl font-extrabold text-slate-900 text-center font-[Rubik]">
          تسجيل الدخول
        </h1>
        <input
          type="text"
          placeholder="اسم المستخدم"
          className="w-full border border-slate-300 rounded-xl p-3 bg-white/40 backdrop-blur focus:outline-none focus:ring-2 focus:ring-indigo-400 font-[Rubik] text-slate-800 shadow"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="كلمة المرور"
          className="w-full border border-slate-300 rounded-xl p-3 bg-white/40 backdrop-blur focus:outline-none focus:ring-2 focus:ring-indigo-400 font-[Rubik] text-slate-800 shadow"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button
          onClick={handleLogin}
          className="w-full bg-indigo-500/80 hover:bg-indigo-500 text-white font-semibold py-2.5 px-5 rounded-xl shadow transition font-[Rubik]"
        >
          دخول
        </button>
        <button
          onClick={() => router.push("/signup")}
          className="w-full mt-2 bg-emerald-500/80 hover:bg-emerald-500 text-white font-semibold py-2.5 px-5 rounded-xl shadow transition font-[Rubik]"
        >
          إنشاء حساب جديد
        </button>
      </motion.div>
    </div>
  );
}
