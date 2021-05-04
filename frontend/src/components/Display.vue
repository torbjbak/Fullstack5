<template>
    <fieldset>
        <div id="disp">
            <output>{{ workingString }}</output>
            <output>= {{ answer }}</output>
        </div>
    </fieldset>
</template>

<script>
export default {
    name: 'Display',

    props: {
        workingString: {
            type: String,
            required: true
        },
        answer: {
            type: String,
            required: true
        }
    }
}
</script>

<style scoped>
#disp {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    min-height: 40px;
}
</style>
