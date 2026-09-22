import React from 'react';
import { NavLink } from 'react-router-dom';
import {
  LayoutDashboard,
  Users,
  ShieldCheck,
  FileText,
  Users2,
  BookmarkCheck,
  Compass,
  Layers,
  Sparkles
} from 'lucide-react';
import { useAuth } from '../../context/AuthContext';

export const Sidebar = ({ isOpen }) => {
  const { user, hasRole } = useAuth();

  const isStudent = hasRole('ROLE_USER');
  const isAdmin = hasRole('ROLE_ADMIN');
  const isPrincipal = hasRole('ROLE_PRINCIPAL');
  const isTeacher = hasRole('ROLE_TEACHER');

  const navItems = [
    { name: 'Dashboard', path: '/dashboard', icon: LayoutDashboard },
    { name: 'Thesis Topics', path: '/topics', icon: FileText },
    ...(isStudent
      ? [
          { name: 'My Team', path: '/teams', icon: Users2 },
          { name: 'Matchmaking Board', path: '/matchmaking', icon: Compass },
        ]
      : []),
    { name: 'Topic Registrations', path: '/registrations', icon: BookmarkCheck },
    ...(isAdmin || isPrincipal || isTeacher
      ? [
          {
            name: isAdmin ? 'User Management' : 'Teaching Staff',
            path: '/users',
            icon: Users,
          },
        ]
      : []),
    ...(isAdmin
      ? [{ name: 'Roles & Access', path: '/roles', icon: ShieldCheck }]
      : []),
  ];

  return (
    <aside
      className={`fixed inset-y-0 left-0 z-40 bg-white border-r border-[#ebedf2] transition-all duration-300 flex flex-col ${
        isOpen ? 'w-64' : 'w-20'
      }`}
    >
      {/* Brand Header */}
      <div className="h-16 flex items-center px-6 border-b border-[#ebedf2]">
        <div className="flex items-center space-x-2.5">
          <div className="w-8 h-8 rounded-lg bg-gradient-to-r from-[#da8cff] to-[#9a55ff] flex items-center justify-center text-white shadow-sm">
            <Layers className="w-4 h-4" />
          </div>
          {isOpen && (
            <div className="flex items-baseline space-x-1.5">
              <span className="font-bold text-lg text-[#b66dff] tracking-tight">Purple</span>
              <span className="text-xs font-semibold text-slate-700">Nexus</span>
            </div>
          )}
        </div>
      </div>

      {/* Profile Card (Signature Purple Admin sidebar profile) */}
      {isOpen && (
        <div className="px-5 py-4 border-b border-[#f3f3f3] flex items-center space-x-3">
          <div className="relative">
            <div className="w-10 h-10 rounded-full bg-gradient-to-r from-[#da8cff] to-[#9a55ff] flex items-center justify-center text-white font-bold text-sm">
              {user?.fullName?.charAt(0) || user?.username?.charAt(0) || 'U'}
            </div>
            <span className="absolute bottom-0 right-0 w-2.5 h-2.5 bg-[#1bcfb4] border-2 border-white rounded-full"></span>
          </div>
          <div className="overflow-hidden">
            <p className="text-xs font-bold text-[#343a40] truncate leading-tight">
              {user?.fullName || user?.username || 'David Grey. H'}
            </p>
            <p className="text-[11px] text-slate-400 truncate mt-0.5">
              {user?.roles?.[0]?.replace('ROLE_', '') || 'Project Manager'}
            </p>
          </div>
        </div>
      )}

      {/* Navigation Links */}
      <div className="flex-1 py-4 px-3 space-y-1 overflow-y-auto">
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
                `flex items-center space-x-3 px-4 py-2.5 rounded-lg text-xs font-medium transition-all ${
                  isActive
                    ? 'text-[#b66dff] font-semibold bg-[#f8f2ff]'
                    : 'text-[#495057] hover:text-[#b66dff] hover:bg-[#faf8fd]'
                }`
              }
            >
              <Icon className="w-4 h-4 shrink-0" />
              {isOpen && <span>{item.name}</span>}
            </NavLink>
          );
        })}
      </div>

      {/* Footer System Status */}
      {isOpen && (
        <div className="p-4 border-t border-[#ebedf2] bg-white">
          <div className="flex items-center space-x-2 text-[11px] text-slate-400">
            <div className="w-2 h-2 rounded-full bg-[#1bcfb4]"></div>
            <span>Purple Admin v1.0</span>
          </div>
        </div>
      )}
    </aside>
  );
};
