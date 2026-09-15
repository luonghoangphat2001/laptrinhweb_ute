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
        'CORS & Security Configuration',
        'Database Monitoring & Backup',
        'Approve/Reject Thesis Topics',
        'Review Final Grading Sheets',
      ],
      badgeColor: 'text-brand-400 bg-brand-500/10 border-brand-500/30',
    },
    {
      name: 'ROLE_MANAGER',
      displayName: 'Thesis Supervisor / Department Head',
      description: 'Oversees thesis topics, reviews student proposals, and submits defense evaluation scores.',
      permissions: [
        'Propose & Guide Graduation Topics',
        'Track Student Progress Milestones',
        'Submit Evaluation & Grading Forms',
        'View Department Reports',
      ],
      badgeColor: 'text-emerald-400 bg-emerald-500/10 border-emerald-500/30',
    },
    {
      name: 'ROLE_USER',
      displayName: 'Student / Candidate',
      description: 'Submits thesis registration proposals, uploads defense reports, and views evaluation scores.',
      permissions: [
        'Register Thesis Topic',
        'Upload Bi-weekly Progress Reports',
        'Submit Final Thesis Defense File',
        'View Defense Committee Feedback',
      ],
      badgeColor: 'text-blue-400 bg-blue-500/10 border-blue-500/30',
    },
  ];

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-2xl font-bold text-white tracking-tight flex items-center space-x-2">
          <ShieldCheck className="w-6 h-6 text-brand-500" />
          <span>Role-Based Access Control (RBAC)</span>
        </h1>
        <p className="text-slate-400 text-sm mt-1">
          Defined privileges, permission matrices, and security scoping.
        </p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {roles.map((role) => (
          <div
            key={role.name}
            className="bg-slate-950/60 border border-slate-800/80 rounded-2xl p-6 flex flex-col justify-between shadow-xl shadow-black/20"
          >
            <div>
              <div className="flex items-center justify-between mb-4">
                <span className={`px-2.5 py-1 rounded-lg text-xs font-mono font-semibold border ${role.badgeColor}`}>
                  {role.name}
                </span>
                <Lock className="w-4 h-4 text-slate-500" />
              </div>

              <h2 className="text-lg font-bold text-white mb-2">{role.displayName}</h2>
              <p className="text-slate-400 text-xs leading-relaxed mb-6">
                {role.description}
              </p>

              <div className="space-y-2.5">
                <span className="text-[11px] uppercase font-bold text-slate-500 tracking-wider">
                  Granted Privileges
                </span>
                {role.permissions.map((perm, idx) => (
                  <div key={idx} className="flex items-start space-x-2 text-xs text-slate-300">
                    <Check className="w-3.5 h-3.5 text-brand-400 shrink-0 mt-0.5" />
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
