<template>
    <fieldset class="log">
        <div
            class="logItem"
            v-for="i in logItems"
            :key="i">
            <div>{{ ("0" + new Date(i.dateTime).getHours()).slice(-2) }}:{{("0" + new Date(i.dateTime).getMinutes()).slice(-2)}}</div>
            <div>{{ i.calc }}</div>
        </div>
        
    </fieldset>
</template>

<script>
export default {
    name: 'Log',

    props: {
        logItems: {
            type: Array,
            required: true
        }

    }
}
</script>

<style scoped>
.logItem {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}
</style>
