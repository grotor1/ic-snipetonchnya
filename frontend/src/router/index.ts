import { createRouter, createWebHistory } from 'vue-router';
import { authRoutes } from '@/router/auth';
import { userState } from '@/services/state/user';
import { adminRoutes } from '@/router/admin';
import { clientRoutes } from '@/router/client';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    ...authRoutes,
    ...adminRoutes,
    ...clientRoutes,
  ],
});

router.beforeEach(async () => {
  await userState.init();
})

export default router;
