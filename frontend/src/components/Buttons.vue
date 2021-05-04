<template>
    <section class="calc">
        <article class="btnRow">
            <button class="calcButton" @click="clearBtn">C</button>
            <button class="calcButton" @click="addNumber(answer)">ANS</button>
            <button class="calcButton" @click="deleteBtn">DEL</button>
            <button class="calcButton" @click="addOperator('+')">+</button>
        </article>

        <article class="btnRow">
            <button
                class="calcButton"
                v-for="n in ['1', '2', '3']"
                :key="n"
                @click="addDigit(n)"
                >{{ n }}
            </button>
            <button class="calcButton" @click="addOperator('-')">-</button>
        </article>

        <article class="btnRow">
            <button
                class="calcButton"
                v-for="n in ['4', '5', '6']"
                :key="n"
                @click="addDigit(n)"
                >{{ n }}
            </button>
            <button class="calcButton"  @click="addOperator('*')">*</button>
        </article>

        <article class="btnRow">
            <button
                class="calcButton"
                v-for="n in ['7', '8', '9']"
                :key="n"
                @click="addDigit(n)"
                >{{ n }}
            </button>
            <button class="calcButton"  @click="addOperator('/')">/</button>
        </article>

        <article class="btnRow">
            <button class="calcButton" @click="addNumber('3.14159')">π</button>
            <button class="calcButton" @click="addDigit('0')">0</button>
            <button class="calcButton" @click="addDot">.</button>
            <button class="calcButton" @click="$emit('equals'); clearBtn()">=</button>
        </article>
    </section>
</template>

<script>
export default {
    name: 'Buttons',

    props: {
        answer: {
            type: String,
            required: true
        }
    },

    data() {
        return {
            writeArray: [],
            writeString: '',
            argString: '',
            argCounter: 0,
            currentNr: '',
            hasDot: false
        }
    },

    methods: {
        update() {
            this.writeString = this.writeArray.join('')
            console.log('write', this.writeString)
            console.log('arg', this.argString, this.writeArray[this.argCounter])
            console.log('count', this.argCounter)
            this.$emit('update', this.writeString)
        },
        clearBtn() {
            this.writeArray = []
            this.writeString = ''
            this.argString = ''
            this.argCounter = 0
            this.update()
        },  
        deleteBtn() {
            if(this.argCounter%2 == 0) {
                this.argString = ''
                this.writeArray[this.argCounter] = ''
                this.update()
            }
        },
        addDigit(n) {
            if(this.argCounter%2 == 1) {
                this.argCounter++
            }
            this.argString+=n
            this.writeArray[this.argCounter] = this.argString
            this.update()
        },
        addOperator(o) {
            if(this.argCounter%2 == 0 && this.argString != '') {
                if(this.argString.slice(-1) == '.') {
                    this.argString = this.argString.slice(0, -1)
                    this.writeArray[this.argCounter] = this.argString
                }
                this.argString = ''
                this.argCounter++
                this.hasPoint = false
                this.writeArray.push(o)
            } else if(this.argCounter%2 == 1) {
                this.writeArray[this.argCounter] = o
            }
            this.update()
        },
        addDot() {
            if(!this.hasPoint) {
                if(this.argCounter%2 == 0) {
                    if(this.argString == '') {
                        this.argString = '0'
                    }
                    this.argString += ('.')
                } else {
                    this.argCounter++
                    this.argString = '0.'
                }
                this.writeArray[this.argCounter] = this.argString
                this.hasPoint = true
                this.update()
            }
        },
        addNumber(n) {
            if(this.argCounter%2 == 1) {
                this.argCounter++
            }
            this.argString = 'null'
            this.writeArray[this.argCounter] = n
            this.hasPoint = true
            this.update()
        }
    }
}
</script>

<style scoped>
.calc {
    width: 320px;
    margin: 0 auto 0 auto;
}
.calcButton {
    font-size: 30px;
    font-family: Arial;
    height: 60px;
    width: 80px;
}
</style>
