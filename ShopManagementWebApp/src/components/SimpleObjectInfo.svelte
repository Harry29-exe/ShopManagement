<script lang="ts">
    export let object: any = {};

    let properties: string[] = [];
    let values: any[] = [];

    for (let prop in object) {
        if (Object.prototype.hasOwnProperty.call(object, prop)) {
            properties.push(prop);
            values.push(object[prop]);
        }
    }
</script>

<div class="v-stack">
    {#each properties as prop, i}
        <div class="h-stack">
            <div >{prop}</div>
            <div class="font-bold">{values[i]}</div>
        </div>
    {/each}
</div>