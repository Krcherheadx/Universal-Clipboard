"use client";
import { useState } from "react";
import { motion } from "framer-motion";
import { ClipboardIcon, EyeIcon, EyeOffIcon } from "../page";

// Login Page Component
export const LoginPage = ({ onNavigate }: any) => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    setIsLoading(true);

    // Simulate API call
    setTimeout(() => {
      // Store username in localStorage (replace with actual login logic)
      localStorage.setItem("username", formData.email.split("@")[0]);
      setIsLoading(false);
      // Navigate to dashboard (you would implement this)
      console.log("Login successful!");
    }, 1000);
  };

  const handleChange = (e: any) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  return (
    <div className="min-h-screen bg-slate-50 flex items-center justify-center p-4">
      <motion.div
        className="bg-white rounded-xl shadow-sm border border-slate-200 w-full max-w-md p-8"
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
      >
        <div className="text-center mb-8">
          <div className="flex items-center justify-center gap-2 mb-4">
            <ClipboardIcon className="w-8 h-8 text-blue-600" />
            <span className="text-2xl font-bold text-slate-900">ClipSync</span>
          </div>
          <h1 className="text-2xl font-bold text-slate-900 mb-2">
            Welcome back
          </h1>
          <p className="text-slate-600">Sign in to your account</p>
        </div>

        <div className="space-y-6">
          <div>
            <label className="block text-sm font-medium text-slate-700 mb-2">
              Email
            </label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              className="w-full px-3 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              placeholder="Enter your email"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-slate-700 mb-2">
              Password
            </label>
            <div className="relative">
              <input
                type={showPassword ? "text" : "password"}
                name="password"
                value={formData.password}
                onChange={handleChange}
                className="w-full px-3 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent pr-10"
                placeholder="Enter your password"
              />
              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                className="absolute right-3 top-2.5 text-slate-400 hover:text-slate-600"
              >
                {showPassword ? <EyeOffIcon /> : <EyeIcon />}
              </button>
            </div>
          </div>

          <button
            onClick={handleSubmit}
            disabled={isLoading}
            className="w-full py-2 px-4 bg-blue-600 hover:bg-blue-700 disabled:bg-blue-400 text-white font-medium rounded-lg transition-colors"
          >
            {isLoading ? "Signing in..." : "Sign In"}
          </button>
        </div>

        <div className="mt-6 text-center">
          <p className="text-slate-600">
            Don't have an account?{" "}
            <button
              onClick={() => onNavigate("signup")}
              className="text-blue-600 hover:text-blue-700 font-medium"
            >
              Sign up
            </button>
          </p>
        </div>

        <div className="mt-4 text-center">
          <button
            onClick={() => onNavigate("landing")}
            className="text-slate-500 hover:text-slate-600 text-sm"
          >
            ← Back to home
          </button>
        </div>
      </motion.div>
    </div>
  );
};
