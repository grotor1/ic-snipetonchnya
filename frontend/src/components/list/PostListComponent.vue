<template>
  <div class="max-w-full flex flex-col gap-4">
    <post-card-component v-for="(post, key) in posts" :key :post @delete="() => loadPosts()"/>
  </div>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">
import type {IPost, IPostFilters} from "@/api/post/types";
import PostCardComponent from "@/components/block/PostCardComponent.vue";
import {ref, watch} from "vue";
import useRequest from "@/api/hooks/useRequest";
import type {IPagination, TId} from "@/api/default/types";
import {getParamsFromObject} from "@/assets/utils/paramsGenerator";

interface IProps {
  params?: IPostFilters,
  search?: TId,
}

const props = defineProps<IProps>();

const posts = ref<IPost[]>([]);

const loadPosts = async () => {
  if (props.search) {
    posts.value = await useRequest().get(`/searches/${props.search}/extract`);
  } else {
    const paramsString = getParamsFromObject(props.params ?? {});
    posts.value = await useRequest().get(`/posts?${paramsString}`);
  }
}

watch(() => props.params, () => {
  loadPosts();
}, {deep: true, immediate: true});
</script>

<style>

</style>
