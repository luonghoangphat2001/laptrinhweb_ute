import React from 'react';
import { NavLink } from 'react-router-dom';
import { LayoutDashboard, Users, ShieldCheck, FileText, Settings, Layers } from 'lucide-react';
import { useAuth } from '../../context/AuthContext';

export const Sidebar = ({ isOpen }) => {
  const { hasRole } = useAuth();

  const navItems = [
    { name: 'Dashboard', path: '/dashboard', icon: LayoutDashboard },
    { name: 'User Management', path: '/users', icon: Users, adminOnly: true },
    { name: 'Roles & Access', path: '/roles', icon: ShieldCheck, adminOnly: true },
    { name: 'Thesis & Topics', path: '/topics', icon: FileText },
    { name: 'System Settings', path: '/settings', icon: Settings },
  ];

  return (
    <aside
      className={`fixed inset-y-0 left-0 z-40 bg-slate-950 border-r border-slate-800 transition-all duration-300 flex flex-col ${
        isOpen ? 'w-64' : 'w-20'
      }`}
    >
      {/* Brand Header */}
      <div className="h-16 flex items-center px-6 border-b border-slate-800/80">
        <div className="flex items-center space-x-3">
          <div className="w-9 h-9 rounded-xl bg-gradient-to-tr from-brand-600 to-emerald-400 flex items-center justify-center text-white shadow-lg shadow-brand-500/25">
            <Layers className="w-5 h-5" />
          </div>
          {isOpen && (
            <div className="flex flex-col">
              <span className="font-bold text-base text-white tracking-tight">Nexus Portal</span>
              <span className="text-[10px] text-brand-500 font-mono font-medium tracking-wider uppercase">Enterprise v1.0</span>
            </div>
          )}
        </div>
      </div>

      {/* Navigation Links */}
      <div className="flex-1 py-6 px-3 space-y-1.5 overflow-y-auto">
        {navItems.map((item) => {
          if (item.adminOnly && !hasRole('ROLE_ADMIN')) {
            return null;
          }

          const Icon = item.icon;

          return (
            <NavLink
              key={item.path}
              to={item.path}
              className={({ isActive }) =>
                `flex items-center space-x-3 px-3.5 py-3 rounded-xl text-sm font-medium transition-all ${
                  isActive
                    ? 'bg-brand-500/10 text-brand-400 border border-brand-500/30 shadow-sm'
                    : 'text-slate-400 hover:text-slate-200 hover:bg-slate-900/60'
                }`
              }
            >
              <Icon className="w-5 h-5 shrink-0" />
              {isOpen && <span>{item.name}</span>}
            </NavLink>
          );
        })}
      </div>

      {/* Footer Info */}
      {isOpen && (
        <div className="p-4 border-t border-slate-800/80 bg-slate-900/30">
          <div className="flex items-center space-x-2 text-[11px] text-slate-500">
            <div className="w-2 h-2 rounded-full bg-emerald-500"></div>
            <span>Backend: Connected (MySQL)</span>
          </div>
        </div>
      )}
    </aside>
  );
};
