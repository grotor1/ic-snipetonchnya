<template>
  <base-client-page>
    <div class="flex flex-col p-6 gap-4 h-full overflow-y-auto">
      <search-card-component v-if="search" :search="search"/>

      <post-list-component v-if="getId" :id="getId"/>
    </div>
  </base-client-page>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import BaseClientPage from "@/views/client/BaseClientPage.vue";
import {useRoute} from "vue-router";
import {computed, ref, watch} from "vue";
import type { IPost } from "@/api/post/types";
import type {ISearch} from "@/api/search/types";
import useRequest from "@/api/hooks/useRequest";
import PostListComponent from "@/components/list/PostListComponent.vue";
import LabelComponent from "@/components/label/LabelComponent.vue";
import CardBlockComponent from "@/components/block/CardBlockComponent.vue";
import ButtonComponent from "@/components/button/ButtonComponent.vue";
import TextInputComponent from "@/components/input/TextInputComponent.vue";
import ButtonSelectComponent from "@/components/select/ButtonSelectComponent.vue";
import type {IFilters} from "@/api/default/types";
import SearchCardComponent from "@/components/block/SearchCardComponent.vue";

const route = useRoute();

const search = ref<ISearch>();

const getId = computed(() => route.params.id as string);

const load = async () => {
  if (getId.value) {
    search.value = await useRequest().get(`/searches/${getId.value}`);
  }
}

load();
</script>

<style>

</style>
