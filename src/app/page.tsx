"use client";
import { useRouter } from "next/navigation";
import { motion } from "framer-motion";
import { ClipboardDocumentListIcon } from "@heroicons/react/24/solid";

export default function Home() {
  const router = useRouter();

  return (
    <main
      dir="rtl"
      className="min-h-screen bg-gradient-to-br from-slate-100 via-slate-200 to-slate-300 flex items-center justify-center p-6 font-[Rubik]"
    >
      <motion.div
        className="bg-white/60 backdrop-blur-lg rounded-2xl shadow-2xl ring-1 ring-slate-200 border border-slate-300 p-10 w-full max-w-xl text-center space-y-6"
        initial={{ opacity: 0, y: 40 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.6 }}
      >
        <ClipboardDocumentListIcon className="h-12 w-12 text-indigo-500 mx-auto drop-shadow" />
        <h1 className="text-3xl font-extrabold text-slate-900 font-[Rubik]">
          الحافظة السحابية
        </h1>
        <p className="text-slate-600 font-[Rubik]">
          احفظ، وزامن، وأدرّ الحافظة الخاصة بك عبر جميع أجهزتك بسهولة.
        </p>
        <div className="flex flex-col sm:flex-row gap-4 justify-center">
          <button
            onClick={() => router.push("/login")}
            className="bg-indigo-500/80 hover:bg-indigo-500 text-white font-semibold py-2.5 px-5 rounded-xl shadow transition font-[Rubik]"
          >
            تسجيل الدخول
          </button>
          <button
            onClick={() => router.push("/signup")}
            className="bg-emerald-500/80 hover:bg-emerald-500 text-white font-semibold py-2.5 px-5 rounded-xl shadow transition font-[Rubik]"
          >
            إنشاء حساب
          </button>
        </div>
      </motion.div>
    </main>
  );
}
