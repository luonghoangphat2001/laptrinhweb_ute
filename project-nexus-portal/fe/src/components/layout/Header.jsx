import React, { useState } from 'react';
import { useAuth } from '../../context/AuthContext';
import { Bell, Search, LogOut, User as UserIcon, Shield, Menu } from 'lucide-react';

export const Header = ({ onToggleSidebar }) => {
  const { user, logout } = useAuth();
  const [dropdownOpen, setDropdownOpen] = useState(false);

  return (
    <header className="h-16 bg-slate-900/80 backdrop-blur-md border-b border-slate-800 px-4 md:px-8 flex items-center justify-between sticky top-0 z-30">
      <div className="flex items-center space-x-4">
        <button
          onClick={onToggleSidebar}
          className="p-2 rounded-lg text-slate-400 hover:text-white hover:bg-slate-800 focus:outline-none transition-colors"
          title="Toggle Navigation Menu"
        >
          <Menu className="w-5 h-5" />
        </button>

        {/* Global Search */}
        <div className="hidden sm:flex items-center relative">
          <Search className="w-4 h-4 text-slate-400 absolute left-3 pointer-events-none" />
          <input
            type="text"
            placeholder="Search resources, topics, users..."
            className="bg-slate-950 border border-slate-800 rounded-xl pl-9 pr-4 py-1.5 text-sm text-slate-200 placeholder-slate-500 focus:outline-none focus:border-brand-500/60 focus:ring-1 focus:ring-brand-500/60 w-64 md:w-80 transition-all"
          />
        </div>
      </div>

      <div className="flex items-center space-x-3 sm:space-x-4">
        {/* Notifications Icon */}
        <button className="p-2 text-slate-400 hover:text-white hover:bg-slate-800 rounded-xl transition-colors relative">
          <Bell className="w-5 h-5" />
          <span className="w-2 h-2 bg-brand-500 rounded-full absolute top-2 right-2 ring-2 ring-slate-900 animate-pulse"></span>
        </button>

        <div className="h-6 w-px bg-slate-800"></div>

        {/* User Profile Dropdown */}
        <div className="relative">
          <button
            onClick={() => setDropdownOpen(!dropdownOpen)}
            className="flex items-center space-x-3 p-1 rounded-xl hover:bg-slate-800/80 transition-colors text-left"
          >
            <div className="w-8 h-8 rounded-lg bg-gradient-to-tr from-brand-600 to-emerald-500 flex items-center justify-center font-bold text-white text-xs shadow-md shadow-brand-500/20">
              {user?.fullName?.charAt(0) || user?.username?.charAt(0) || 'U'}
            </div>
            <div className="hidden md:block">
              <p className="text-xs font-semibold text-white leading-tight">
                {user?.fullName || user?.username || 'Authenticated User'}
              </p>
              <div className="flex items-center space-x-1 mt-0.5">
                <Shield className="w-3 h-3 text-brand-500" />
                <span className="text-[10px] text-slate-400 font-mono">
                  {user?.roles?.[0]?.replace('ROLE_', '') || 'USER'}
                </span>
              </div>
            </div>
          </button>

          {dropdownOpen && (
            <div className="absolute right-0 mt-2 w-56 bg-slate-900 border border-slate-800 rounded-2xl shadow-xl shadow-black/50 py-2 z-50 animate-in fade-in slide-in-from-top-2 duration-150">
              <div className="px-4 py-2 border-b border-slate-800/60">
                <p className="text-xs text-slate-400">Signed in as</p>
                <p className="text-sm font-semibold text-white truncate">{user?.email || 'user@nexus.com'}</p>
              </div>

              <div className="py-1">
                <button
                  onClick={() => setDropdownOpen(false)}
                  className="w-full text-left px-4 py-2 text-sm text-slate-300 hover:bg-slate-800 flex items-center space-x-2 transition-colors"
                >
                  <UserIcon className="w-4 h-4 text-slate-400" />
                  <span>Profile Overview</span>
                </button>
              </div>

              <div className="pt-1 border-t border-slate-800/60">
                <button
                  onClick={() => {
                    setDropdownOpen(false);
                    logout();
                  }}
                  className="w-full text-left px-4 py-2 text-sm text-red-400 hover:bg-red-500/10 flex items-center space-x-2 transition-colors"
                >
                  <LogOut className="w-4 h-4 text-red-400" />
                  <span>Sign Out</span>
                </button>
              </div>
            </div>
          )}
        </div>
      </div>
    </header>
  );
};
