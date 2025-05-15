<template>
  <base-client-page>
    <div class="p-6 h-full overflow-y-auto">
      <post-list-component/>
    </div>
  </base-client-page>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">
import PostListComponent from "@/components/list/PostListComponent.vue";
import BaseClientPage from "@/views/client/BaseClientPage.vue";
</script>

<style>

</style>
