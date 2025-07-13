"use client";
import { motion } from "framer-motion";

import {
  ClipboardIcon,
  DevicesIcon,
  ShieldIcon,
  SyncIcon,
  ZapIcon,
} from "../page";

export const LandingPage = ({ onNavigate }: any) => {
  const features = [
    {
      icon: <SyncIcon className="w-8 h-8" />,
      title: "Real-time Sync",
      description:
        "Your clipboard content syncs instantly across all connected devices with WebSocket technology.",
    },
    {
      icon: <ZapIcon className="w-8 h-8" />,
      title: "Lightning Fast",
      description:
        "Copy, paste, and save content with lightning speed. No delays, no waiting.",
    },
    {
      icon: <ShieldIcon className="w-8 h-8" />,
      title: "Secure & Private",
      description:
        "Your data is encrypted and secure. Only you have access to your clipboard content.",
    },
    {
      icon: <DevicesIcon className="w-8 h-8" />,
      title: "Cross-platform",
      description:
        "Works seamlessly on desktop, mobile, and tablet. Access your clipboard anywhere.",
    },
  ];

  return (
    <div className="min-h-screen bg-slate-50">
      {/* Header */}
      <header className="bg-white border-b border-slate-200">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between items-center h-16">
            <div className="flex items-center gap-2">
              <ClipboardIcon className="w-8 h-8 text-blue-600" />
              <span className="text-xl font-bold text-slate-900">ClipSync</span>
            </div>
            <div className="flex gap-4">
              <button
                onClick={() => onNavigate("login")}
                className="px-4 py-2 text-slate-700 hover:text-slate-900 font-medium"
              >
                Login
              </button>
              <button
                onClick={() => onNavigate("signup")}
                className="px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white font-medium rounded-lg transition-colors"
              >
                Sign Up
              </button>
            </div>
          </div>
        </div>
      </header>

      {/* Hero Section */}
      <section className="py-20 px-4 sm:px-6 lg:px-8">
        <div className="max-w-4xl mx-auto text-center">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6 }}
          >
            <h1 className="text-5xl font-bold text-slate-900 mb-6 tracking-tight">
              Your clipboard, <span className="text-blue-600">everywhere</span>
            </h1>
            <p className="text-xl text-slate-600 mb-8 max-w-2xl mx-auto">
              Store, sync, and access your clipboard content across all your
              devices in real-time. Simple, fast, and secure.
            </p>
            <div className="flex gap-4 justify-center">
              <button
                onClick={() => onNavigate("signup")}
                className="px-8 py-3 bg-blue-600 hover:bg-blue-700 text-white font-semibold rounded-lg transition-colors"
              >
                Get Started Free
              </button>
              <button
                onClick={() => onNavigate("login")}
                className="px-8 py-3 bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold rounded-lg transition-colors"
              >
                Sign In
              </button>
            </div>
          </motion.div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-20 px-4 sm:px-6 lg:px-8 bg-white">
        <div className="max-w-6xl mx-auto">
          <div className="text-center mb-16">
            <h2 className="text-3xl font-bold text-slate-900 mb-4">
              Everything you need in a clipboard manager
            </h2>
            <p className="text-lg text-slate-600">
              Powerful features designed to make your workflow seamless
            </p>
          </div>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
            {features.map((feature, index) => (
              <motion.div
                key={index}
                className="text-center p-6"
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ delay: index * 0.1, duration: 0.5 }}
              >
                <div className="w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center mx-auto mb-4 text-blue-600">
                  {feature.icon}
                </div>
                <h3 className="text-xl font-semibold text-slate-900 mb-2">
                  {feature.title}
                </h3>
                <p className="text-slate-600">{feature.description}</p>
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-20 px-4 sm:px-6 lg:px-8">
        <div className="max-w-4xl mx-auto text-center">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6 }}
          >
            <h2 className="text-3xl font-bold text-slate-900 mb-4">
              Ready to sync your clipboard?
            </h2>
            <p className="text-lg text-slate-600 mb-8">
              Join thousands of users who trust ClipSync for their daily
              workflow
            </p>
            <button
              onClick={() => onNavigate("signup")}
              className="px-8 py-3 bg-blue-600 hover:bg-blue-700 text-white font-semibold rounded-lg transition-colors"
            >
              Start Free Today
            </button>
          </motion.div>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-white border-t border-slate-200 py-8">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between items-center">
            <div className="flex items-center gap-2">
              <ClipboardIcon className="w-6 h-6 text-blue-600" />
              <span className="font-bold text-slate-900">ClipSync</span>
            </div>
            <p className="text-slate-600">
              © 2025 ClipSync. All rights reserved.
            </p>
          </div>
        </div>
      </footer>
    </div>
  );
};
