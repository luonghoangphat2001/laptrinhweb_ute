/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        // Purple Admin signature color palette
        brand: {
          50: '#f8f2ff',
          100: '#f1e2ff',
          200: '#e1c2ff',
          500: '#b66dff',
          600: '#a34cfc',
          700: '#8e27eb',
        },
        purple: {
          50: '#f8f2ff',
          100: '#f1e2ff',
          400: '#da8cff',
          500: '#b66dff',
          600: '#9a55ff',
          700: '#6a008a',
        },
        body: {
          bg: '#f2f4f9', // Exact Purple Admin background
        },
        slate: {
          50: '#f8f9fa',
          100: '#f2f4f9',
          200: '#ebedf2',
          300: '#dee2e6',
          400: '#ced4da',
          500: '#9c9fa6',
          600: '#6c757d',
          700: '#495057',
          800: '#343a40',
          900: '#212529',
        }
      },
      backgroundImage: {
        'purple-gradient-primary': 'linear-gradient(to right, #da8cff, #9a55ff)',
        'purple-gradient-danger': 'linear-gradient(to right, #ffbf96, #fe7096)',
        'purple-gradient-info': 'linear-gradient(to right, #90caf9, #047edf 99%)',
        'purple-gradient-success': 'linear-gradient(to right, #84d9d2, #07cdae)',
      }
    },
  },
  plugins: [],
}
