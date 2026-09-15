import React, { useEffect, useState } from 'react';
import api from '../services/api';
import { Users, ShieldCheck, Activity, Clock, ArrowUpRight, CheckCircle2, AlertTriangle } from 'lucide-react';

export const DashboardPage = () => {
  const [stats, setStats] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchStats = async () => {
      setLoading(true);
      setError(null);
      try {
        const res = await api.get('/dashboard/stats');
        if (res && res.data) {
          setStats(res.data);
        } else {
          setStats(null);
        }
      } catch (err) {
        setError(err.message);
        setStats(null);
      } finally {
        setLoading(false);
      }
    };

    fetchStats();
  }, []);

  const kpis = [
    {
      label: 'Total Registered Users',
      value: stats ? stats.totalUsers : 0,
      icon: Users,
      color: 'from-blue-500 to-indigo-600',
      change: 'Active in system',
    },
    {
      label: 'Active Accounts',
      value: stats ? stats.activeUsers : 0,
      icon: CheckCircle2,
      color: 'from-emerald-500 to-teal-600',
      change: 'Verified accounts',
    },
    {
      label: 'Configured Roles',
      value: stats ? stats.totalRoles : 0,
      icon: ShieldCheck,
      color: 'from-brand-500 to-emerald-600',
      change: 'RBAC permissions',
    },
    {
      label: 'Service Uptime',
      value: stats ? `${Math.floor(stats.systemUptimeSeconds / 60)} min` : '0 min',
      icon: Clock,
      color: 'from-amber-500 to-orange-600',
      change: 'JVM runtime',
    },
  ];

  return (
    <div className="space-y-8">
      {/* Page Title & Breadcrumb */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="text-2xl md:text-3xl font-bold text-white tracking-tight">
            Enterprise Dashboard Overview
          </h1>
          <p className="text-slate-400 text-sm mt-1">
            Real-time telemetry, user management metrics and thesis monitoring.
          </p>
        </div>

        <div className="flex items-center space-x-3">
          {error ? (
            <span className="inline-flex items-center px-3 py-1 rounded-full text-xs font-medium bg-red-500/10 text-red-400 border border-red-500/20">
              <span className="w-1.5 h-1.5 rounded-full bg-red-400 mr-2"></span>
              Backend API Offline
            </span>
          ) : (
            <span className="inline-flex items-center px-3 py-1 rounded-full text-xs font-medium bg-emerald-500/10 text-emerald-400 border border-emerald-500/20">
              <span className="w-1.5 h-1.5 rounded-full bg-emerald-400 mr-2 animate-pulse"></span>
              Spring Boot 3 API Online
            </span>
          )}
        </div>
      </div>

      {/* Explicit Error Banner */}
      {error && (
        <div className="p-4 rounded-2xl bg-red-500/10 border border-red-500/30 flex items-start space-x-3 text-red-400 text-sm">
          <AlertTriangle className="w-5 h-5 shrink-0 mt-0.5" />
          <div>
            <p className="font-semibold text-red-300">Could Not Connect to Backend Service</p>
            <p className="text-xs mt-0.5 text-red-400">{error}</p>
          </div>
        </div>
      )}

      {/* KPI Cards Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-5">
        {kpis.map((kpi, idx) => {
          const Icon = kpi.icon;
          return (
            <div
              key={idx}
              className="bg-slate-950/60 border border-slate-800/80 rounded-2xl p-5 hover:border-slate-700 transition-all shadow-lg shadow-black/20 relative overflow-hidden group"
            >
              <div className="flex items-center justify-between">
                <span className="text-xs font-semibold text-slate-400 uppercase tracking-wider">
                  {kpi.label}
                </span>
                <div className={`w-10 h-10 rounded-xl bg-gradient-to-tr ${kpi.color} flex items-center justify-center text-white shadow-md`}>
                  <Icon className="w-5 h-5" />
                </div>
              </div>
              <div className="mt-4 flex items-baseline justify-between">
                <span className="text-3xl font-bold text-white tracking-tight">
                  {loading ? '...' : kpi.value}
                </span>
              </div>
              <p className="mt-2 text-xs text-slate-500 flex items-center">
                <ArrowUpRight className="w-3.5 h-3.5 text-brand-400 mr-1 inline" />
                <span>{kpi.change}</span>
              </p>
            </div>
          );
        })}
      </div>

      {/* Analytics Breakdown & Recent Activity */}
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Role Distribution Panel */}
        <div className="bg-slate-950/60 border border-slate-800/80 rounded-2xl p-6 shadow-lg shadow-black/20">
          <h2 className="text-base font-bold text-white flex items-center space-x-2">
            <ShieldCheck className="w-4 h-4 text-brand-500" />
            <span>Role Distribution</span>
          </h2>
          <p className="text-slate-400 text-xs mt-1">
            Configured permissions & user categorization
          </p>

          <div className="mt-6 space-y-4">
            {stats && stats.userRoleDistribution ? (
              Object.entries(stats.userRoleDistribution).map(([role, count]) => (
                <div key={role} className="space-y-1.5">
                  <div className="flex justify-between text-xs">
                    <span className="font-mono text-slate-300">{role.replace('ROLE_', '')}</span>
                    <span className="text-slate-400 font-semibold">{count} user(s)</span>
                  </div>
                  <div className="w-full h-2 bg-slate-800 rounded-full overflow-hidden">
                    <div
                      className="h-full bg-gradient-to-r from-brand-500 to-emerald-400 rounded-full"
                      style={{ width: `${Math.min(100, (count / (stats.totalUsers || 1)) * 100)}%` }}
                    ></div>
                  </div>
                </div>
              ))
            ) : (
              <p className="text-xs text-slate-500 py-4 text-center">No role metrics available.</p>
            )}
          </div>
        </div>

        {/* Recent Activities Log */}
        <div className="lg:col-span-2 bg-slate-950/60 border border-slate-800/80 rounded-2xl p-6 shadow-lg shadow-black/20">
          <div className="flex items-center justify-between mb-4">
            <div>
              <h2 className="text-base font-bold text-white flex items-center space-x-2">
                <Activity className="w-4 h-4 text-brand-500" />
                <span>Recent System Activities</span>
              </h2>
              <p className="text-slate-400 text-xs mt-1">
                Audit trail of administrative actions and registrations
              </p>
            </div>
          </div>

          <div className="divide-y divide-slate-800/60">
            {stats && stats.recentActivities && stats.recentActivities.length > 0 ? (
              stats.recentActivities.map((act, i) => (
                <div key={i} className="py-3.5 flex items-center justify-between text-sm">
                  <div className="flex items-center space-x-3">
                    <div className="w-2 h-2 rounded-full bg-brand-500"></div>
                    <span className="text-slate-200 font-medium">{act.action}</span>
                    <span className="text-xs text-brand-400 font-mono">@{act.username}</span>
                  </div>
                  <span className="text-xs text-slate-500">{new Date(act.timestamp).toLocaleTimeString()}</span>
                </div>
              ))
            ) : (
              <div className="py-8 text-center text-slate-500 text-sm">
                No recent activity records found.
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};
