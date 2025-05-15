<template>
  <div class="flex-col flex gap-4">
    <card-block-component v-if="isLoggedIn" class="p-4 flex flex-col gap-4">
      <text-input-component v-model="comment" placeholder="Комментарий" />

      <button-component @click="handleCreateComment"
                        :disabled="!comment">
        Оставить комментарий
      </button-component>
    </card-block-component>

    <card-block-component v-for="(comment, key) in comments" :key class="p-4 flex flex-col gap-4">
      <div class="flex justify-between items-center">
        <router-link :to="`/auth/account/${comment.author.id}`" class="hover:underline">
          {{ comment.author.login }} {{ comment.author.fullName ? `- ${comment.author.fullName}` : '' }}
        </router-link>

        <span>
          {{ new Date(comment.createdAt).toLocaleDateString() }}
        </span>
      </div>

      <span>
        {{ comment.content }}
      </span>
    </card-block-component>
  </div>

</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import { ref } from 'vue';
import type { IComment } from '@/api/comment/types';
import useRequest from '@/api/hooks/useRequest';
import TextInputComponent from '@/components/input/TextInputComponent.vue';
import CardBlockComponent from '@/components/block/CardBlockComponent.vue';
import ButtonComponent from '@/components/button/ButtonComponent.vue';
import { userState } from '@/services/state/user';

interface IProps {
  postId: string;
}

const {isLoggedIn} = userState;

const props = defineProps<IProps>();

const comment = ref<string>('');

const comments = ref<IComment[]>([]);

const loadComments = async () => {
  comments.value = (await useRequest().get(`/posts/${props.postId}`)).comments;
};

loadComments();

const handleCreateComment = async () => {
  await useRequest().post(`/comments`, {
    content: comment.value,
    postId: props.postId,
  });

  comment.value = '';
  await loadComments();
};
</script>

<style>

</style>
