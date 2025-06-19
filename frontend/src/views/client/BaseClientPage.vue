<template>
  <base-page>
    <div class="md:grid md:grid-cols-[250px,1fr] md:h-[calc(100vh-65px)]">
      <sidebar-component class="hidden md:block"/>

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
