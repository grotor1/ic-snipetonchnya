<template>
  <base-page>
    <div class="grid grid-cols-[250px,1fr] h-[calc(100vh-66px)]">
      <sidebar-component/>

      <slot/>
    </div>
  </base-page>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import BasePage from '@/views/BasePage.vue';
import SidebarComponent from '@/components/sidebar/SidebarComponent.vue';
</script>

<style>

</style>
