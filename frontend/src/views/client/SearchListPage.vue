<template>
  <base-client-page>
    <div class="p-6 h-full overflow-y-auto flex flex-col gap-4">
      <search-card-component v-for="(search, index) in searches" :key="index" :search="search"/>
    </div>
  </base-client-page>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">
import BaseClientPage from "@/views/client/BaseClientPage.vue";
import {ref} from "vue";
import type {ISearch} from "@/api/search/types";
import useRequest from "@/api/hooks/useRequest";
import SearchCardComponent from "@/components/block/SearchCardComponent.vue";

const searches = ref<ISearch[]>([]);

const load = async () => {
  searches.value = await useRequest().get(`/searches`);
}

load();
</script>

<style>

</style>
