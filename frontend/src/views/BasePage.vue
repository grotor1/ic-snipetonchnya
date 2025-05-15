<template>
  <div class="w-full h-screen bg-stone-900 text-amber-100">
    <header-component/>

    <slot/>
  </div>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import HeaderComponent from '@/components/header/HeaderComponent.vue';
</script>

<style>

</style>
