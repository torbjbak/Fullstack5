<template>
    <main>
        <Display
            :workingString="workingString"
            :answer="answer"
        />
        <Buttons 
            :answer="answer"
            @update="updateDisp"
            @equals="equals"
        />
        <Log :logItems="logItems"/>
    </main>
</template>

<script>
import { onMounted, ref } from "vue";
import Display from './components/Display.vue'
import Buttons from './components/Buttons.vue'
import Log from './components/Log.vue'
import { GET, POST, baseURL, request } from "@/utility/request.js";

export default {
    name: 'App',

    components: {
        Display,
        Buttons,
        Log
    }, 

    setup() {
        const workingString = ref('')
        const logItems = ref([])
        const answer = ref('')

        const updateDisp = async (data) => {
            workingString.value = data
        }

        const equals = async () => {
            const response = await request(`${baseURL}`, POST, workingString.value);

            if (response.status === 201) {
                console.log(response.data)
                answer.value = response.data
            } else {
                console.error("An error occured while sending in calculation!")
            }
            getLogs()
        }

        const getLogs = async () => {
            const response = await request(`${baseURL}`, GET);
            if (response.status === 201) {
                logItems.value = response.data
            } else {
                console.error("An error occured while fetching logs!")
            }
        }

        onMounted(async () => {
            getLogs()
        })

        return {
            workingString,
            logItems,
            answer,
            updateDisp,
            equals
        }
    }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  text-align: center;
  margin-top: 5px;

}
main{
}
fieldset {
    width: 300px;
    padding: 8px;
    margin: 0;
    display: flex;
    margin: 0 auto 0 auto;
    flex-direction: column;
    font-family: Courier;
    font-size: large;
}
output {
    text-align: right;
}
</style>
