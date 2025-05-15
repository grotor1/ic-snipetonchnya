<template>
  <router-link :to="`/search/${search.id}`">
    <card-block-component class="p-6 flex-col flex gap-4">
      <div class="flex justify-between items-center">
        <span class="text-2xl">
          {{ search.name }}
        </span>

        <span>
          {{ new Date(search.createdAt).toLocaleDateString() }}
        </span>
      </div>

      <span>
         Поиск названия: {{ search.titleEntry }}
      </span>

      <div class="flex gap-2 flex-wrap">
        <router-link v-for="(tag, key) in search.tags" :key :to="`/search?tags=${tag.id}`">
          <button-component :full-width="false">
            {{ tag.label }}
          </button-component>
        </router-link>

        <router-link v-for="(tech, key) in search.techs" :key :to="`/search?techs=${tech.id}`">
          <button-component :full-width="false">
            {{ tech.label }}
          </button-component>
        </router-link>

      </div>
    </card-block-component>
  </router-link>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import type {ISearch} from "@/api/search/types";
import CardBlockComponent from "@/components/block/CardBlockComponent.vue";
import ButtonComponent from "@/components/button/ButtonComponent.vue";

interface IProps {
  search: ISearch,
}

const props = defineProps<IProps>()
</script>

<style>

</style>
