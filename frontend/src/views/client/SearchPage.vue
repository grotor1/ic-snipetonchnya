<template>
  <base-client-page>
    <div class="flex flex-col p-6 gap-4 h-full overflow-y-auto">
      <card-block-component class="p-6 flex flex-col gap-4">
        <label-component label="Название">
          <text-input-component v-model="requestParams.titleEntry"/>
        </label-component>

        <label-component div label="Теги">
          <button-select-component :options="getTags"
                                   v-model="requestParams.tags"
                                   multiple/>
        </label-component>

        <label-component div label="Технологии">
          <button-select-component :options="getTechs"
                                   v-model="requestParams.techs"
                                   multiple/>
        </label-component>

        <button-component @click="handleSearch">
          Найти
        </button-component>

        <template v-if="isLoggedIn">
          <label-component label="Название поиска">
            <text-input-component v-model="name"/>
          </label-component>

          <button-component v-if="isLoggedIn" @click="handleSave">
            Сохранить
          </button-component>
        </template>
      </card-block-component>

      <post-list-component v-if="isSearched" :params="renderParams"/>
    </div>
  </base-client-page>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">
import BaseClientPage from "@/views/client/BaseClientPage.vue";
import {useRoute, useRouter} from "vue-router";
import CardBlockComponent from "@/components/block/CardBlockComponent.vue";
import LabelComponent from "@/components/label/LabelComponent.vue";
import TextInputComponent from "@/components/input/TextInputComponent.vue";
import {computed, ref, watch} from "vue";
import type {IPostFilters} from "@/api/post/types";
import ButtonSelectComponent from "@/components/select/ButtonSelectComponent.vue";
import type {ITech} from "@/api/tech/types";
import type {ITag} from "@/api/tag/types";
import useRequest from "@/api/hooks/useRequest";
import ButtonComponent from "@/components/button/ButtonComponent.vue";
import PostListComponent from "@/components/list/PostListComponent.vue";
import {userState} from "@/services/state/user";

const route = useRoute();
const router = useRouter();

const {isLoggedIn} = userState;

const techs = ref<ITech[]>([]);
const tags = ref<ITag[]>([]);

const requestParams = ref<Required<Omit<IPostFilters, 'author'>>>({
  titleEntry: '',
  tags: [],
  techs: [],
});

const renderParams = ref<Required<Omit<IPostFilters, 'author'>>>();

const isSearched = ref<boolean>(false);

const name = ref('');


const getTechs = computed(() => techs.value.map((tech) => ({
  label: tech.label,
  value: tech.id,
})))

const getTags = computed(() => tags.value.map((tag) => ({
  label: tag.label,
  value: tag.id,
})))


const init = async () => {
  await Promise.all([
    (async () => {
      techs.value = await useRequest().get('/techs');
    })(),
    (async () => {
      tags.value = await useRequest().get('/tags');
    })()
  ])

  let flag = false;

  if (route.query.techs) {
    if (Array.isArray(route.query.techs)) {
      requestParams.value.techs = route.query.techs.map((item) => item?.toString()).filter<string>((item): item is string => typeof item !== 'undefined');
    } else {
      requestParams.value.techs = [route.query.techs];
    }
  }

  if (route.query.tags) {
    if (Array.isArray(route.query.tags)) {
      requestParams.value.tags = route.query.tags.map((item) => item?.toString()).filter<string>((item): item is string => typeof item !== 'undefined');
    } else {
      requestParams.value.tags = [route.query.tags];
    }
    flag = true;
  }

  if (route.query.titleEntry && !Array.isArray(route.query.titleEntry)) {
    requestParams.value.titleEntry = route.query.titleEntry;
    flag = true;
  }

  if (flag) {
    renderParams.value = JSON.parse(JSON.stringify(requestParams.value));
    isSearched.value = true;
  }
}

const handleSearch = async () => {
  renderParams.value = JSON.parse(JSON.stringify(requestParams.value));
  isSearched.value = true;
}

const handleSave = async () => {
  await useRequest().post(`/searches`, {
    ...requestParams.value,
    name: name.value,
  });
}

watch(requestParams, (value) => {
  router.replace({path: route.path, query: value});
}, {deep: true});

init();
</script>

<style>

</style>
