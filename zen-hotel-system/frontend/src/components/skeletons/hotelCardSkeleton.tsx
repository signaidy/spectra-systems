export function HotelCardSkeleton() {
  return (
    <article className="border rounded-md grid-cols-[0.75fr_1fr] grid h-72 animate-pulse bg-muted">
      <div className="relative bg-muted-foreground rounded-l-md" />
      <div className="flex flex-col justify-between rounded-r-md">
        <div className="flex flex-col gap-y-1 p-4">
          <div className="w-96 h-8 bg-muted-foreground rounded-md" />
          <div className="w-80 h-5 bg-muted-foreground rounded-md" />
          <div className="flex flex-col gap-y-6 mt-5">
            <div className="w-full h-10 bg-muted-foreground rounded-md" />
            <div className="w-36 h-5 bg-muted-foreground rounded-md" />
          </div>
        </div>
        <div className="border-t p-4 flex justify-end gap-x-3">
          <div className="flex gap-x-1 items-end">
            <div className="h-7 w-32 bg-muted-foreground rounded-md" />
          </div>
          <div className="w-28 h-10 bg-muted-foreground rounded-md" />
        </div>
      </div>
    </article>
  );
}
