<template>
  <base-client-page>
    <div class="p-6 flex flex-col gap-4 h-full overflow-y-auto">
      <post-card-component v-if="post" :post @delete="router.push('/')"/>

      <comment-block-component v-if="post" :post-id="post.id"/>
    </div>
  </base-client-page>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import BaseClientPage from '@/views/client/BaseClientPage.vue';
import PostCardComponent from '@/components/block/PostCardComponent.vue';
import { ref } from 'vue';
import type { IPost } from '@/api/post/types';
import {useRoute, useRouter} from 'vue-router';
import useRequest from '@/api/hooks/useRequest';
import CommentBlockComponent from '@/components/block/CommentBlockComponent.vue';

const route = useRoute();
const router = useRouter();

const post = ref<IPost>();

const loadPost = async () => {
  if (route.params.id) {
    post.value = await useRequest().get(`/posts/${route.params.id}`);
  }
}

loadPost();
</script>

<style>

</style>
