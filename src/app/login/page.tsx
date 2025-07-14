"use client";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { motion } from "framer-motion";
import { LockClosedIcon } from "@heroicons/react/24/solid";

export default function LoginPage() {
  const router = useRouter();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {
    localStorage.setItem("username", username);
    router.push("/dashboard");
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
      </motion.div>
    </div>
  );
}
