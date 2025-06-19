<template>
  <div class="relative w-full h-screen flex flex-col text-amber-100">
    <header-component class="hidden md:flex"/>

    <div class="pb-14 md:pb-0 bg-stone-900 grow flex flex-col">
      <slot/>
    </div>

    <footer-component/>
  </div>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import HeaderComponent from '@/components/header/HeaderComponent.vue';
import FooterComponent from "@/components/footer/FooterComponent.vue";
</script>

<style>

</style>
