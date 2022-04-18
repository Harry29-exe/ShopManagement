<script lang="ts">
    import Table from "../../components/table/Table.svelte";
    let header: string[] = [
        "id", "has correction", "entity id",
        "issue date", "is payed", "details"
    ]
    let sampleValue: any[][] = [
        [1, false, 2, "31-03-2021", false],
        [2, false, 12, "31-03-2021", false],
        [3, true, 17, "31-03-2021", false],
        [4, false, 17, "31-03-2021", true],
    ]

</script>


<div class="w-min-[320px] w-11/12 bg-bg-200 v-stack mt-20 mx-auto py-4 rounded-md">
    <button class="center w-4/5 bg-bg-300 text-3xl btn-lg">+</button>

    <div class="table-wrapper w-4/5">
        <Table header={header} values={sampleValue}/>
    </div>
</div>