import React from 'react';
import { ShieldCheck, Lock, Check } from 'lucide-react';

export const RolesPage = () => {
  const roles = [
    {
      name: 'ROLE_ADMIN',
      displayName: 'System Administrator',
      description: 'Unrestricted access to all configuration, security filters, database operations and user control.',
      permissions: [
        'User Management & Role Assignment',
        'Academic Data Management (Khoa, Bộ môn, Ngành)',
        'Database Monitoring & Telemetry',
        'Approve/Reject Thesis Topics',
        'Systemwide Audit Records',
      ],
      badgeStyle: 'bg-brand-50 text-brand-700 border-brand-200',
    },
    {
      name: 'ROLE_TEACHER',
      displayName: 'Lecturer & Advisor (GVHD)',
      description: 'Proposes graduation thesis topics (1-2 advisors, target majors), reviews student team registrations.',
      permissions: [
        'Propose & Manage Capstone Topics',
        'Assign Co-Advisors & Target Majors',
        'Review Team Registration Applications',
        'Submit Feedback & Guidance Notes',
      ],
      badgeStyle: 'bg-indigo-50 text-indigo-700 border-indigo-200',
    },
    {
      name: 'ROLE_USER',
      displayName: 'Student / Candidate',
      description: 'Discovers and compares topics, forms teams of 1-3 members, registers topics, and posts on matchmaking board.',
      permissions: [
        'Discover & Compare up to 3 Topics',
        'Form & Manage Team Roster (1-3 students)',
        'Submit Official Topic Registration',
        'Team Matchmaking & Candidate Collaboration',
      ],
      badgeStyle: 'bg-emerald-50 text-emerald-700 border-emerald-200',
    },
  ];

  return (
    <div className="space-y-6 animate-in fade-in duration-200">
      <div>
        <h1 className="text-2xl font-bold text-slate-800 tracking-tight flex items-center space-x-2">
          <ShieldCheck className="w-6 h-6 text-brand-600" />
          <span>Role-Based Access Control (RBAC)</span>
        </h1>
        <p className="text-slate-500 text-sm mt-0.5">
          Defined privileges, permission matrices, and security scoping.
        </p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {roles.map((role) => (
          <div
            key={role.name}
            className="bg-white border border-slate-200/80 rounded-2xl p-6 flex flex-col justify-between shadow-xs hover:shadow-md transition-all"
          >
            <div>
              <div className="flex items-center justify-between mb-4">
                <span className={`px-2.5 py-1 rounded-lg text-xs font-mono font-semibold border ${role.badgeStyle}`}>
                  {role.name}
                </span>
                <Lock className="w-4 h-4 text-slate-400" />
              </div>

              <h2 className="text-lg font-bold text-slate-800 mb-2">{role.displayName}</h2>
              <p className="text-slate-500 text-xs leading-relaxed mb-6">{role.description}</p>

              <div className="space-y-2.5">
                <span className="text-[11px] uppercase font-bold text-slate-400 tracking-wider">
                  Granted Privileges
                </span>
                {role.permissions.map((perm, idx) => (
                  <div key={idx} className="flex items-start space-x-2 text-xs text-slate-600">
                    <Check className="w-3.5 h-3.5 text-brand-600 shrink-0 mt-0.5" />
                    <span>{perm}</span>
                  </div>
                ))}
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default RolesPage;
