<template>
  <card-block-component class="p-6 flex flex-col gap-4 post-card" :id="`${post.title}`">
    <div class="flex justify-between items-center">
      <router-link :to="`/post/${post.id}`" id="post-card-retrieve-link" class="hover:underline">
        <span class="text-2xl">
          {{ post.title }}
        </span>
      </router-link>

      <span>
        {{ new Date(post.createdAt).toLocaleDateString() }}
      </span>

      <router-link :to="`/auth/account/${post.author.id}`" class="hover:underline">
        {{ post.author.login }} {{ post.author.fullName ? `- ${post.author.fullName}` : '' }}
      </router-link>
    </div>

    <div class="flex gap-2 flex-wrap">
      <router-link v-for="(tag, key) in post.tags" :key :to="`/search?tags=${tag.id}`">
        <button-component :full-width="false">
          {{ tag.label }}
        </button-component>
      </router-link>

      <router-link v-for="(tech, key) in post.techs" :key :to="`/search?techs=${tech.id}`">
        <button-component :full-width="false">
          {{ tech.label }}
        </button-component>
      </router-link>

    </div>

    <vue-monaco-editor :value="post.content"
                       :language="post.language"
                       theme="vs-dark"
                       height="600px"
                       :options="{
      readOnly: true,
    }"/>

    <div v-if="userState.getIsAdmin.value" class="flex gap-2 flex-wrap">
      <button-component :full-width="false" @click="deleteHandler" id="post-card-delete-button">
        Удалить
      </button-component>
    </div>
  </card-block-component>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import CardBlockComponent from '@/components/block/CardBlockComponent.vue';
import type {IPost} from '@/api/post/types';
import ButtonComponent from '@/components/button/ButtonComponent.vue';
import {userState} from "@/services/state/user";
import useRequest from "@/api/hooks/useRequest";
import {useRouter} from "vue-router";

interface IEmits {
  (event: 'delete'): void;
}

interface IProps {
  post: IPost;
}

const router = useRouter();

const emits = defineEmits<IEmits>();
const props = defineProps<IProps>();

const deleteHandler = async () => {
  await useRequest().delete(`/posts/${props.post.id}`);
  emits('delete');
}
</script>

<style>

</style>
