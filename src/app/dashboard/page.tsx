"use client";
import { useEffect, useState } from "react";
import { motion } from "framer-motion";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";
import {
  ClipboardDocumentCheckIcon,
  TrashIcon,
} from "@heroicons/react/24/solid";

export default function DashboardPage() {
  const [clipboards, setClipboards] = useState<
    { id: string; content: string; createdAt: Date }[]
  >([]);
  const [input, setInput] = useState("");
  const [stompClient, setStompClient] = useState<Client | null>(null);

  useEffect(() => {
    const socket = new SockJS("http://localhost:8080/ws");
    const client = new Client({
      webSocketFactory: () => socket as any,
      reconnectDelay: 5000,
      onConnect: () => {
        client.publish({
          destination: "/app/greeting",
          body: JSON.stringify({ name: "Hisham" }),
        });
        client.subscribe("/topic/clips", (message) => {
          if (message.body) {
            const data: { content: string; createdAt: Date; id: string } =
              JSON.parse(message.body);
            setClipboards((prev) => [data, ...prev]);
          }
        });
      },
      onStompError: (frame) => {
        console.error("Broker error:", frame.headers["message"]);
      },
    });

    client.activate();
    setStompClient(client);

    return () => {
      client.deactivate();
    };
  }, []);

  const handlePaste = async () => {
    try {
      const text = await navigator.clipboard.readText();
      setInput(text);
    } catch (err) {
      console.error("فشل الوصول إلى الحافظة:", err);
    }
  };

  const handleSave = () => {
    if (input.trim() && stompClient && stompClient.connected) {
      stompClient.publish({
        destination: "/app/add",
        headers: { "content-type": "application/json" },
        body: JSON.stringify({ content: input }),
      });
      setInput("");
    }
  };

  const handleDelete = (index: string) => {
    // هنا يمكنك إرسال طلب حذف للباكند
    console.log("طلب حذف للعنصر ذو المعرف:", index);
  };

  return (
    <div
      className="min-h-screen bg-gradient-to-br from-slate-100 via-slate-200 to-slate-300 flex items-center justify-center p-6 font-[Rubik]"
      dir="rtl"
    >
      <motion.div
        className="max-w-xl w-full mx-auto bg-white/60 backdrop-blur-lg rounded-2xl shadow-2xl ring-1 ring-slate-200 p-8 space-y-6 border border-slate-300"
        initial={{ opacity: 0, y: 30 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.6 }}
      >
        <div className="flex items-center gap-2">
          <ClipboardDocumentCheckIcon className="h-7 w-7 text-indigo-500 drop-shadow" />
          <h1 className="text-2xl font-bold text-slate-900">مرحباً بك،</h1>
        </div>

        <textarea
          placeholder="الصق محتوى الحافظة هنا..."
          className="w-full h-24 p-4 rounded-xl border border-slate-300 bg-white/40 backdrop-blur focus:outline-none focus:ring-2 focus:ring-indigo-400 font-[Rubik] text-slate-800"
          value={input}
          onChange={(e) => setInput(e.target.value)}
        />

        <div className="flex gap-3">
          <button
            onClick={handlePaste}
            className="bg-yellow-400/80 hover:bg-yellow-400 text-slate-900 font-semibold px-4 py-2 rounded-xl shadow transition font-[Rubik]"
          >
            لصق
          </button>
          <button
            onClick={handleSave}
            className="bg-indigo-500/80 hover:bg-indigo-500 text-white font-semibold px-4 py-2 rounded-xl shadow transition font-[Rubik]"
          >
            حفظ
          </button>
        </div>

        <ul className="space-y-3">
          {clipboards.map((clip, idx) => (
            <motion.li
              key={clip.id}
              className="bg-white/60 backdrop-blur p-4 rounded-xl shadow flex justify-between items-center border border-slate-200"
              initial={{ opacity: 0, y: 10 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: 0.03 * idx }}
            >
              <div>
                <span className="break-all text-slate-900 font-[Rubik]">
                  {clip.content}
                </span>
                <div className="text-xs text-slate-500 mt-1 font-[Rubik]">
                  {new Date(clip.createdAt).toLocaleString("ar-EG")}
                </div>
              </div>
              <div className="flex gap-2 ml-4">
                <button
                  onClick={() => navigator.clipboard.writeText(clip.content)}
                  className="bg-yellow-300/80 hover:bg-yellow-400 px-3 py-1 text-sm font-semibold rounded-xl shadow font-[Rubik] text-slate-900"
                >
                  نسخ
                </button>
                <button
                  onClick={() => handleDelete(clip.id)}
                  className="bg-red-500/80 hover:bg-red-500 text-white px-3 py-1 rounded-xl shadow font-[Rubik]"
                >
                  <TrashIcon className="h-4 w-4" />
                </button>
              </div>
            </motion.li>
          ))}
        </ul>
      </motion.div>
    </div>
  );
}
