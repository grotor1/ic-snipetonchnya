import type { RouteRecordRaw } from 'vue-router';

export const clientRoutes: RouteRecordRaw[] = [
  {
    name: 'ClientRoutes',
    path: '/',
    children: [
      {
        name: 'CreatePostPage',
        path: 'create-post',
        component: () => import('@/views/client/CreatePostPage.vue')
      },
      {
        name: 'PostListPage',
        path: '',
        component: () => import('@/views/client/PostListPage.vue')
      },
      {
        name: 'SearchPage',
        path: 'search',
        component: () => import('@/views/client/SearchPage.vue'),
      },
      {
        name: 'SearchRetrievePage',
        path: 'search/:id',
        component: () => import('@/views/client/SearchRetrievePage.vue'),
      },
      {
        name: 'SearchListPage',
        path: 'searches-list',
        component: () => import('@/views/client/SearchListPage.vue'),
      },
      {
        name: 'PostRetrievePage',
        path: '/post/:id',
        component: () => import('@/views/client/PostRetrievePage.vue'),
      },
    ]
  },
];
