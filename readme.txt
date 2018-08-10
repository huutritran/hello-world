 private fun showDialogAddDegree() {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_add_degree, view as ViewGroup, false)
        val time: EditText = view.tvTime
        val place: EditText = view.tvPlace
        val degree: EditText = view.tvDegreeName
        val description: EditText = view.tvDescription

        val alertDialog = AlertDialog.Builder(context)
                .setView(view)
                .setTitle("Thêm Bằng Cấp")
                .setPositiveButton("Thêm", null)
                .setNegativeButton("Hủy", null)
                .create()
        alertDialog.setOnShowListener {
            val positiveButton = (alertDialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                val timString = time.text.toString()
                val placeStr = place.text.toString()
                val degreeStr = degree.text.toString()
                val descriptionStr = description.text.toString()
                val error = when {
                    time.text.toString().isEmpty() -> "Xin hãy nhập thời gian"
                    place.text.toString().isEmpty() -> "Xin hãy nhập địa điểm"
                    degree.text.toString().isEmpty() -> "Xin hãy nhập tên bằng cấp"
                    else -> null
                }
                if (null != error) {
                    toast(error)
                } else {
                    val degreeMap = hashMapOf<String, Any>(CellConstant.LayoutId to R.layout.item_bound_degree,
                            CellConstant.DisplayInfo to DegreeCell.generateDisplay(timString, placeStr, degreeStr, descriptionStr))
                    val cell = GenerateCellUtil.generateCell(degreeMap)
                    val section = adapter.dataList[0]
                    section.apply {
                        listItem.add(listItem.lastIndex, cell)
                        adapter.notifyItemChanged(0)
                    }
                    alertDialog.dismiss()
                }
            }
        }
        alertDialog.show()

    }
