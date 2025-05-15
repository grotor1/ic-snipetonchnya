import type { RouteRecordRaw } from 'vue-router';

export const authRoutes: RouteRecordRaw[] = [
  {
    name: 'AuthRoutes',
    path: '/auth',
    children: [
      {
        name: 'RegistrationPage',
        path: 'registration',
        component: () => import('@/views/auth/RegistrationPage.vue')
      },
      {
        name: 'LoginPage',
        path: 'login',
        component: () => import('@/views/auth/LoginPage.vue')
      },
      {
        name: 'AccountPage',
        path: 'account/:id',
        component: () => import('@/views/auth/AccountPage.vue')
      }
    ]
  },
];
