<template>
  <div class="flex w-full flex-wrap gap-2">
    <button-component
        v-for="{ label, value } in options"
        :key="value"
        :id="`${idPrefix}-${value}-button`"
        :active="currentValue.includes(value)"
        :full-width="false"
        @click="handleClick(value)"
    >
      {{ label }}
    </button-component>
  </div>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">
import { computed } from 'vue';
import ButtonComponent from '@/components/button/ButtonComponent.vue';

interface IEmits {
  (event: 'update:modelValue', value: string[]): void;
}

interface IProps {
  modelValue: string[];
  options: {
    label: string;
    value: string;
  }[];
  multiple?: boolean;
  idPrefix?: string;
}

const props = defineProps<IProps>();
const emits = defineEmits<IEmits>();

const currentValue = computed({
  get: () => props.modelValue,
  set: (value: string[]) => emits('update:modelValue', value),
});

const handleClick = (value: string) => {
  if (currentValue.value.includes(value)) {
    currentValue.value.splice(currentValue.value.indexOf(value), 1);
  } else if (props.multiple) {
    currentValue.value.push(value);
  } else {
    currentValue.value = [value];
  }
};
</script>

<style></style>
