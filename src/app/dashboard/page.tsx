"use client";
import { useEffect, useState, useRef } from "react";
import { motion } from "framer-motion";

// Icons as React components
const ClipboardIcon = ({ className = "w-4 h-4" }) => (
  <svg
    className={className}
    viewBox="0 0 24 24"
    fill="none"
    stroke="currentColor"
    strokeWidth="2"
  >
    <path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path>
    <rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect>
  </svg>
);

const SaveIcon = ({ className = "w-4 h-4" }) => (
  <svg
    className={className}
    viewBox="0 0 24 24"
    fill="none"
    stroke="currentColor"
    strokeWidth="2"
  >
    <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11l5 5v11a2 2 0 0 1-2 2z"></path>
    <polyline points="17,21 17,13 7,13 7,21"></polyline>
    <polyline points="7,3 7,8 15,8"></polyline>
  </svg>
);

const CopyIcon = ({ className = "w-4 h-4" }) => (
  <svg
    className={className}
    viewBox="0 0 24 24"
    fill="none"
    stroke="currentColor"
    strokeWidth="2"
  >
    <rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
    <path d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path>
  </svg>
);

const DeleteIcon = ({ className = "w-4 h-4" }) => (
  <svg
    className={className}
    viewBox="0 0 24 24"
    fill="none"
    stroke="currentColor"
    strokeWidth="2"
  >
    <polyline points="3,6 5,6 21,6"></polyline>
    <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
  </svg>
);

const ConnectedIcon = ({ className = "w-4 h-4" }) => (
  <svg
    className={className}
    viewBox="0 0 24 24"
    fill="none"
    stroke="currentColor"
    strokeWidth="2"
  >
    <path d="M9 12l2 2 4-4"></path>
    <circle cx="12" cy="12" r="10"></circle>
  </svg>
);

const DisconnectedIcon = ({ className = "w-4 h-4" }) => (
  <svg
    className={className}
    viewBox="0 0 24 24"
    fill="none"
    stroke="currentColor"
    strokeWidth="2"
  >
    <circle cx="12" cy="12" r="10"></circle>
    <line x1="4.93" y1="4.93" x2="19.07" y2="19.07"></line>
  </svg>
);

export default function DashboardPage() {
  const [clipboards, setClipboards] = useState<string[]>([]);
  const [input, setInput] = useState("");
  const [isConnected, setIsConnected] = useState(false);
  const [copyFeedback, setCopyFeedback] = useState<number | null>(null);
  const socketRef = useRef<WebSocket | null>(null);

  useEffect(() => {
    // Using native WebSocket instead of SockJS for this example
    // Replace with your SockJS implementation
    const connectWebSocket = () => {
      try {
        const sock = new WebSocket("ws://localhost:8080/ws");
        socketRef.current = sock;

        console.log("🔌 WebSocket connecting...");

        sock.onopen = () => {
          console.log("🔌 WebSocket connected");
          setIsConnected(true);
        };

        sock.onmessage = (e) => {
          const message = e.data;
          setClipboards((prev) => [message, ...prev]);
        };

        sock.onclose = () => {
          console.log("❌ WebSocket disconnected");
          setIsConnected(false);
          // Attempt to reconnect after 3 seconds
          setTimeout(connectWebSocket, 3000);
        };

        sock.onerror = (error) => {
          console.error("WebSocket error event:", event);
          console.error("WebSocket readyState:", sock.readyState);
          setIsConnected(false);
        };
      } catch (error) {
        console.error("Failed to connect WebSocket:", error);
        setIsConnected(false);
        setTimeout(connectWebSocket, 3000);
      }
    };

    connectWebSocket();

    return () => {
      if (socketRef.current) {
        socketRef.current.close();
      }
    };
  }, []);

  const handlePaste = async () => {
    try {
      const text = await navigator.clipboard.readText();
      setInput(text);
    } catch (err) {
      console.error("Clipboard access failed:", err);
    }
  };

  const handleSave = () => {
    if (input.trim() && socketRef.current && isConnected) {
      socketRef.current.send(input);
      setInput("");
    }
  };

  const handleCopy = async (clip: string, index: number) => {
    try {
      await navigator.clipboard.writeText(clip);
      setCopyFeedback(index);
      setTimeout(() => setCopyFeedback(null), 1000);
    } catch (err) {
      console.error("Copy failed:", err);
    }
  };

  const handleDelete = (index: number) => {
    setClipboards((prev) => prev.filter((_, i) => i !== index));
  };

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === "Enter" && e.ctrlKey) {
      handleSave();
    }
  };

  return (
    <div className="min-h-screen bg-slate-50 p-6">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <motion.div
          className="text-center mb-8"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
        >
          <h1 className="text-4xl font-bold text-slate-900 mb-2 tracking-tight">
            ClipSync
          </h1>
          <p className="text-lg text-slate-600">Welcome back, </p>
        </motion.div>

        {/* Connection Status */}
        <motion.div
          className="flex items-center justify-center gap-2 mb-8"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.2 }}
        >
          <div
            className={`flex items-center gap-2 px-4 py-2 rounded-full text-sm font-medium ${
              isConnected
                ? "bg-green-100 text-green-700 border border-green-200"
                : "bg-red-100 text-red-700 border border-red-200"
            }`}
          >
            {isConnected ? (
              <>
                <ConnectedIcon className="w-4 h-4" />
                Connected
              </>
            ) : (
              <>
                <DisconnectedIcon className="w-4 h-4" />
                Disconnected
              </>
            )}
          </div>
        </motion.div>

        {/* Input Section */}
        <motion.div
          className="bg-white border border-slate-200 rounded-xl p-6 mb-8 shadow-sm"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.3 }}
        >
          <div className="space-y-4">
            <textarea
              placeholder="Type or paste your content here..."
              className="w-full h-32 p-4 border border-slate-300 rounded-lg text-slate-900 placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent resize-none"
              value={input}
              onChange={(e) => setInput(e.target.value)}
              onKeyDown={handleKeyDown}
            />
            <div className="flex gap-3">
              <button
                onClick={handlePaste}
                className="flex items-center gap-2 px-4 py-2 bg-slate-100 hover:bg-slate-200 text-slate-700 font-medium rounded-lg transition-colors"
              >
                <ClipboardIcon />
                Paste
              </button>
              <button
                onClick={handleSave}
                disabled={!input.trim() || !isConnected}
                className="flex items-center gap-2 px-4 py-2 bg-blue-600 hover:bg-blue-700 disabled:bg-slate-300 disabled:cursor-not-allowed text-white font-medium rounded-lg transition-colors"
              >
                <SaveIcon />
                Save
              </button>
            </div>
            <p className="text-xs text-slate-500">
              Press Ctrl+Enter to save quickly
            </p>
          </div>
        </motion.div>

        {/* Clipboard Items */}
        <div className="space-y-4">
          {clipboards.length === 0 ? (
            <motion.div
              className="text-center py-12"
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              transition={{ delay: 0.4 }}
            >
              <ClipboardIcon className="w-12 h-12 text-slate-400 mx-auto mb-4" />
              <h3 className="text-lg font-medium text-slate-900 mb-2">
                No clipboard items yet
              </h3>
              <p className="text-slate-500">
                Your saved content will appear here
              </p>
            </motion.div>
          ) : (
            clipboards.map((clip, idx) => (
              <motion.div
                key={idx}
                className="bg-white border border-slate-200 rounded-lg p-4 shadow-sm hover:shadow-md transition-shadow"
                initial={{ opacity: 0, y: 10 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ delay: 0.05 * idx }}
              >
                <div className="flex justify-between items-start gap-4">
                  <div className="flex-1 min-w-0">
                    <div className="bg-slate-50 p-3 rounded-md border border-slate-200">
                      <pre className="text-sm text-slate-900 whitespace-pre-wrap break-words font-mono">
                        {clip}
                      </pre>
                    </div>
                  </div>
                  <div className="flex gap-2 flex-shrink-0">
                    <button
                      onClick={() => handleCopy(clip, idx)}
                      className={`flex items-center gap-1 px-3 py-1.5 text-sm font-medium rounded-md transition-colors ${
                        copyFeedback === idx
                          ? "bg-green-100 text-green-700 border border-green-200"
                          : "bg-slate-100 hover:bg-slate-200 text-slate-700 border border-slate-200"
                      }`}
                    >
                      <CopyIcon className="w-3 h-3" />
                      {copyFeedback === idx ? "Copied!" : "Copy"}
                    </button>
                    <button
                      onClick={() => handleDelete(idx)}
                      className="flex items-center gap-1 px-3 py-1.5 bg-red-100 hover:bg-red-200 text-red-700 text-sm font-medium rounded-md transition-colors border border-red-200"
                    >
                      <DeleteIcon className="w-3 h-3" />
                      Delete
                    </button>
                  </div>
                </div>
              </motion.div>
            ))
          )}
        </div>
      </div>
    </div>
  );
}
