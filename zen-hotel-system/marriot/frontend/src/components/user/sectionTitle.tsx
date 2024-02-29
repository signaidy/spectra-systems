import { InformativeTooltip } from "@/components/ui/informativeTooltip";

export function SectionTitle({
  title,
  description,
}: {
  title: string;
  description: string;
}) {
  return (
    <div className="flex gap-x-3">
      <h1 className="font-bold text-xl">{title}</h1>
      <InformativeTooltip description={description} />
    </div>
  );
}
