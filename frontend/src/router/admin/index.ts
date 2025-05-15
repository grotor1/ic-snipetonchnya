import type { RouteRecordRaw } from 'vue-router';
import { userState } from '@/services/state/user';

export const adminRoutes: RouteRecordRaw[] = [
  {
    name: 'AdminRoutes',
    path: '/admin',
    beforeEnter: () => {
      if (!userState.isLoggedIn.value) {
        return '/auth/login';
      } else if (!userState.getIsAdmin.value) {
        return '/';
      }
    },
    children: [
      {
        name: 'AdminAccountList',
        path: 'accounts',
        component: () => import('@/views/admin/AccountPage.vue'),
      }
    ]
  },
];
