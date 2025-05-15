<template>
  <base-client-page>
    <div class="w-full p-6 flex flex-col gap-4 h-full overflow-y-auto">
      <span class="text-2xl">
         Создание сниппета
      </span>

      <label-component label="Название">
        <text-input-component id="create-post-name" v-model="post.title" placeholder="Название" />
      </label-component>

      <label-component div label="Язык">
        <button-select-component :options="languages"
                                 :model-value="[post.language]"
                                 id-prefix="create-post-language"
                                 @update:model-value="value => post.language = value[0]" />
      </label-component>

      <label-component div label="Теги">
        <button-select-component :options="getTags"
                                 id-prefix="create-post-tag"
                                 v-model="post.tags"
                                 multiple/>
      </label-component>

      <label-component div label="Технологии">
        <button-select-component :options="getTechs"
                                 id-prefix="create-post-tech"
                                 v-model="post.techs"
                                 multiple/>
      </label-component>

      <label-component label="Контент">
        <vue-monaco-editor v-model:value="post.content"
                           id="create-post-content"
                           :options="MONACO_EDITOR_OPTIONS"
                           :language="post.language"
                           height="1000px"
                           theme="vs-dark" />
      </label-component>

      <button-component id="create-post-submit-button" @click="createPost"
                        :disabled="!isValid">
        Запостить
      </button-component>
    </div>
  </base-client-page>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import LabelComponent from '@/components/label/LabelComponent.vue';
import BaseClientPage from '@/views/client/BaseClientPage.vue';
import TextInputComponent from '@/components/input/TextInputComponent.vue';
import { computed, ref } from 'vue';
import type { IPostCreate } from '@/api/post/types';
import ButtonSelectComponent from '@/components/select/ButtonSelectComponent.vue';
import ButtonComponent from '@/components/button/ButtonComponent.vue';
import useRequest from '@/api/hooks/useRequest';
import type { ITech } from '@/api/tech/types';
import type { ITag } from '@/api/tag/types';
import {useRouter} from "vue-router";


const languages = [
  {
    label: 'HTML',
    value: 'html',
  },
  {
    label: 'XML',
    value: 'xml',
  },
  {
    label: 'YAML',
    value: 'yaml',
  },
  {
    label: 'TS',
    value: 'typescript',
  },
  {
    label: 'Java',
    value: 'java',
  },
  {
    label: 'C++',
    value: 'cpp',
  },
  {
    label: 'Kotlin',
    value: 'kotlin',
  },
  {
    label: 'MD',
    value: 'markdown',
  },
  {
    label: 'Dockerfile',
    value: 'dockerfile',
  },
  {
    label: 'JS',
    value: 'javascript',
  },
  {
    label: 'CSS',
    value: 'css',
  },
];

const MONACO_EDITOR_OPTIONS = {
  automaticLayout: true,
  formatOnType: true,
  formatOnPaste: true,
};

const router = useRouter();

const techs = ref<ITech[]>([]);
const tags = ref<ITag[]>([]);

const getTechs = computed(() => techs.value.map((tech) => ({
  label: tech.label,
  value: tech.id,
})))

const getTags = computed(() => tags.value.map((tag) => ({
  label: tag.label,
  value: tag.id,
})))

const post = ref<IPostCreate>({
  content: '',
  title: '',
  language: '',
  techs: [],
  tags: [],
});

const isValid = computed<boolean>(() => {
  return !!post.value && !!post.value.content && !!post.value.language && !!post.value.title;
});

const createPost = async () => {
  await useRequest().post(`/posts`, post.value);
  await router.push('/');
};

const init = async () => {
  await Promise.all([
    (async () => {
      techs.value = await useRequest().get('/techs');
    })(),
    (async () => {
      tags.value = await useRequest().get('/tags');
    })()
  ])
}

init();
</script>

<style>

</style>
